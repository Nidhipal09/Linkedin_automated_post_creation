// node CricInfoExtractor.js --excel=TeamsExcel.csv --folder=TeamsData --source="https://www.espncricinfo.com/series/icc-men-s-t20-world-cup-2021-22-1267897/match-results" 

let parser = require("minimist");
let fs = require("fs");
let axios = require("axios");
let jsdom = require("jsdom");
let xl = require("excel4node");
let pdf = require("pdf-lib");
let path = require("path");

let args = parser(process.argv);

let dwnldPromise=axios.get(args.source);   // download info using axios
dwnldPromise.then(function(response){
    let html = response.data;
    let pageHtml = response.data;

    let dom = new jsdom.JSDOM(html);    // read data using jsdom
    let document = dom.window.document;

    let matchInfo = document.querySelectorAll("div.match-score-block");  // Extract required info from the whole HTML page
    let matches = [];  // an array of all the matches

    for(let i=0; i<matchInfo.length; i++){
        let match = {
            t1 : "",  // team 1
            t2 : "",  // team 2
            t1s : "", // team 1 score
            t2s : "", // team 2 score
            result : ""  // result of the match
        };

        let nameps = matchInfo[i].querySelectorAll("p.name");
        match.t1 = nameps[0].textContent;
        match.t2 = nameps[1].textContent;

        let scoreSpans = matchInfo[i].querySelectorAll("span.score");
        if(scoreSpans.length==2){
            match.t1s = scoreSpans[0].textContent;
            match.t2s = scoreSpans[1].textContent;
        }
        else if(scoreSpans.length==1){
            match.t1s = scoreSpans[0].textContent;
            match.t2s = "";
        }
        else{
            match.t1s = "";
            match.t2s = "";
        }

        let resultSpan = matchInfo[i].querySelector("div.match-info > div.status-text > span");
        match.result = resultSpan.textContent;

        matches.push(match);
    }

    let matchesData = JSON.stringify(matches);   // array to json 
    fs.writeFileSync("matches.json", matchesData, "utf-8");  // make a json file to check the required info

    let teams = [];  // creates an array of all teams
    for(let i=0; i<matches.length; i++){   // for team name
       pushTeamInTeamsIfNotPresent(teams, matches[i].t1);   
       pushTeamInTeamsIfNotPresent(teams, matches[i].t2);
    }

    for(let i=0; i<matches.length; i++){  // for matches of individual teams
        pushMatchInAppropriateTeam(teams, matches[i].t1, matches[i].t2, matches[i].t1s, matches[i].t2s, matches[i].result);
        pushMatchInAppropriateTeam(teams, matches[i].t2, matches[i].t1, matches[i].t2s, matches[i].t1s, matches[i].result);
    }

    let teamsData = JSON.stringify(teams);  // array to json
    fs.writeFileSync("teams.json", teamsData, "utf-8"); // make a json file to check the required info

    prepareExcel(teams, args.excel);   // make an excelsheet using excel4node
    prepareFoldersAndPdfs(teams, args.folder); // make pdfs using pdf-lib

}).catch(function(err){
    console.log(err);
})


function pushTeamInTeamsIfNotPresent(teams, teamName){
    let t1idx = -1;
    for(let j=0; j<teams.length; j++){
        if(teams[j].name==teamName){  // team found
            t1idx = j;
            break;
        }
    }

    if(t1idx == -1){  // if the team is not alredy there in teams, make a new team
        let team = {
            name : teamName,
            matches : []
        };
        teams.push(team);
    }
}

function pushMatchInAppropriateTeam(teams, homeTeam, oppo, selfSco, oppoSco, res){
    let tidx = -1;

    for(let j=0; j<teams.length; j++){
        if(teams[j].name==homeTeam) {
            tidx = j;  // team found
            break;
        }
    }

    let team = teams[tidx];  
        team.matches.push({  // push match in the matched matched of the team
        opponent : oppo,
        selfScore : selfSco,
        opponentScore : oppoSco,
        result : res
    });
}

function prepareExcel(teams, excelfile){
    let wb = new xl.Workbook();  // creates a new workbook

    var nameStyle = wb.createStyle({  // styles to design text
    font : {
        size : 20,
        bold: true,
        underline: true,
    }
    });

    var style1 = wb.createStyle({
        font : {
            size : 14,
            color : '#1a0d00',
            bold : true,
            italics: true
        }
    });

    var style2 = wb.createStyle({
        font : {
            color : 'blue'
        },
        alignment: {
            wrapText: true,
            horizontal: 'left'
        }
    }); 

    for(let i=0; i<teams.length; i++){  
        let ws = wb.addWorksheet(teams[i].name);  // creates a new excelsheet for current team
 
        ws.cell(1,2).string("Team- "+teams[i].name).style(nameStyle);

        ws.cell(3,1).string("S no.").style(style1);
        ws.cell(3,2).string("Opponent").style(style1);
        ws.cell(3,3).string("Score").style(style1);
        ws.cell(3,4).string("Opponent score").style(style1);
        ws.cell(3,5).string("Result").style(style1);

        for(let j=0; j<teams[i].matches.length; j++){
            ws.cell(j+4,1).number(j+1);
            ws.cell(j+4,2).string(teams[i].matches[j].opponent);
            ws.cell(j+4,3).string(teams[i].matches[j].selfScore);
            ws.cell(j+4,4).string(teams[i].matches[j].opponentScore);
            ws.cell(j+4,5).string(teams[i].matches[j].result).style(style2);
        }
    }

    wb.write(excelfile);
}

function prepareFoldersAndPdfs(teams, folderName){
    if(fs.existsSync(folderName)==false){
        fs.mkdirSync(folderName);  // creates a folder for all teams with the given name
    }
    else{
        fs.rmdirSync(folderName, {recursive : true});
    }
    

    for(let i=0; i<teams.length; i++){
        let teamFolderName = path.join(folderName, teams[i].name);  

        if(fs.existsSync(teamFolderName)==false){
           fs.mkdirSync(teamFolderName);  // creates a folder for the current team with the given name
        }

        for(let j=0; j<teams[i].matches.length; j++){
            let match = teams[i].matches[j];
            createScoreCardPdf(teamFolderName, teams[i].name, match);  // creates individual scorecards pdfs for each match of the given team
        }
    }
}

function createScoreCardPdf(teamFolderName, teamName, match){
     let resultArray = match.result.split(" ");
     let newResult = "";

     for(let i=0; i<=4; i++){
         newResult += resultArray[i] + " ";
     }

     let matchFileName = path.join(teamFolderName, match.opponent);
     
     let pdfDocument = pdf.PDFDocument;

     let templateBytes = fs.readFileSync("Template.pdf");  // a template file for every match
     let promiseToLoadTheBytes = pdfDocument.load(templateBytes);

     promiseToLoadTheBytes.then(function(pdfDoc){  
        const pages = pdfDoc.getPages();
        let page = pages[0];
        page.drawText(teamName, {   // text with its coordinates(x,y) of positions
            x : 315,
            y : 655,
            size : 20
        });
        page.drawText(match.selfScore, {
            x : 315,
            y : 618,
            size : 20
        });
        page.drawText(match.opponent, {
            x : 315,
            y : 581,
            size : 20
        });
        page.drawText(match.opponentScore, {
            x : 315,
            y : 544,
            size : 20
        });
        page.drawText(newResult, {
            x : 315,
            y : 507,
            size : 12
        });

        let count = 1;        
        let promiseToSave = pdfDoc.save();
        promiseToSave.then(function(changedBytes){
            if(fs.existsSync(matchFileName+".pdf") == true){
                fs.writeFileSync(matchFileName+count+".pdf", changedBytes);
                count++;
            }else{
                fs.writeFileSync(matchFileName+".pdf", changedBytes); // creates a pdf for the current match
            }
        });
      }); 
}




