// node Automation.js --url="https://www.hackerrank.com/" --info=info.json

let parser = require("minimist");
let fs = require("fs");
let puppeteer = require("puppeteer");

let args = parser(process.argv);
let infoJson = fs.readFileSync(args.info, "utf-8");
let info = JSON.parse(infoJson);

// let browserPromise =  puppeteer.launch({ headless : false});
// browserPromise.then(function(browser){
//     let pagePromise = browser.newPage();
//     pagePromise.then(function(page){
//         let urlPromise = page.goto(args.url);
//         urlPromise.then(function(){
//             let browserClosePromise = browser.close();
//             browserClosePromise.then(function(){
//                 console.log("Browser closed.");
//             });
//         });
//     });
// });


// (async () => {
//     let browser = await puppeteer.launch({ headless : false});
//     let page = await browserPromise.newPage();
//     await page.goto(args.url);
//     await page.screenshot({ path: 'screenshot.png' });
  
//     await browser.close();
//   })();


  async function init () {
    let browserPromise = await puppeteer.launch({ 
        headless : false,
        args : [
            '--start-maximized'
        ],
        defaultViewport: null
    });
    let pages = await browserPromise.pages();
    let page = pages[0];

    await page.goto(args.url);

    await page.waitForSelector("a[data-event-action='Login']");
    await page.click("a[data-event-action='Login']");

    await page.waitForSelector("a[href='https://www.hackerrank.com/login']");
    await page.click("a[href='https://www.hackerrank.com/login']");

    await page.waitForSelector("input[name='username']");
    await page.type("input[name='username']", info.username, {delay : 30});

    await page.waitForSelector("input[name='password']");
    await page.type("input[name='password']", info.password, {delay : 30});

    await page.waitForSelector("button[data-analytics='LoginPassword']");
    await page.click("button[data-analytics='LoginPassword']");

    await page.waitForSelector("div.skill-progress-card span.ui-text");
    await page.click("div.skill-progress-card span.ui-text");

    await page.waitForSelector("div.challenge-submit-btn span.ui-text");
    await page.click("div.challenge-submit-btn span.ui-text");

    await page.waitForSelector("div.challenges-list span.ui-text");
    await page.click("div.challenges-list span.ui-text");

    await page.waitForSelector("div.css-y9fljz-control > div.css-1hwfws3");
    await page.click("div.css-y9fljz-control > div.css-1hwfws3");
    
    await page.waitForSelector("div.css-1j2eamm input#select-language-input");
    await page.type("div.css-1j2eamm input#select-language-input", "java 8");

    await page.keyboard.press('Enter');
    
    await page.evaluate(() => {
      let x = document.querySelectorAll("div.view-line");
      
    }); 

    await browserPromise.close();
  }

  init();
