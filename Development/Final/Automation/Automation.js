// node Automation.js --url="https://www.hackerrank.com/auth/login" --info=../info.json

let parser = require("minimist");
let fs = require("fs");
let puppeteer = require("puppeteer");
let { codes } = require("./code");

let args = parser(process.argv);
let infoJson = fs.readFileSync(args.info, "utf-8");
let info = JSON.parse(infoJson);

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

    await page.waitForSelector("input[name='username']");
    await page.type("input[name='username']", info.username);   // login details

    await page.waitForSelector("input[name='password']");
    await page.type("input[name='password']", info.password);

    await page.waitForSelector("button[data-analytics='LoginPassword']");  // click on login button
    await page.click("button[data-analytics='LoginPassword']");

    await page.waitForSelector("div.skill-progress-card span.ui-text");
    await page.click("div.skill-progress-card span.ui-text");   // problem solving

    await page.waitForSelector("div.filters input[value='unsolved']");
    await page.click("div.filters input[value='unsolved']");  //   unsolved unchecked

    await page.waitForSelector("div.filters input[value='solved']");
    await page.click("div.filters input[value='solved']");  //   solved checked

    await page.waitForTimeout(2000);

    await page.waitForSelector("div#contest-challenges-problem  div.challenge-name-details ");
    await page.click("div#contest-challenges-problem  div.challenge-name-details ");  // click on solve challenge button

    await page.waitForSelector("div.css-y9fljz-control > div.css-1hwfws3");
    await page.click("div.css-y9fljz-control > div.css-1hwfws3");  //   click on language selection
    
    await page.waitForSelector("div.css-1j2eamm input#select-language-input");
    await page.type("div.css-1j2eamm input#select-language-input", "java 7");  // selection of java 7

    await page.keyboard.press('Enter');

    await page.waitForSelector("div.checkBoxWrapper input.checkbox-input");
    await page.click("div.checkBoxWrapper input.checkbox-input");  // check custom-input dialog box

    await page.waitForTimeout(3000);

    await page.waitForSelector("div.custom-input textarea#input-1");
    await page.type("div.checkBoxWrapper input.checkbox-input", codes[0].sol);  // write the code in the custom-input dialog box

    await page.keyboard.down('Control')
    await page.keyboard.press('A')   // select the content of custom-input dialog box

    await page.keyboard.down('Control')
    await page.keyboard.press('C')   // copy the content of custom-input dialog box

    await page.click("div.view-lines");

    await page.keyboard.down('Control')
    await page.keyboard.press('A')  // select the content of editor

    await page.keyboard.down('Control')
    await page.keyboard.press('V')   // paste the required code in the dialog box

    await page.waitForSelector("div.checkBoxWrapper input.checkbox-input");
    await page.click("div.checkBoxWrapper input.checkbox-input");  // uncheck the custom-input dialog box
    
    await page.waitForSelector("button.hr-monaco-submit span.ui-text");
    await page.click("button.hr-monaco-submit span.ui-text");  //   click on submit button

    // await browserPromise.close();
  }

  init();
