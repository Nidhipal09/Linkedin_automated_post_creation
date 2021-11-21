// node Linkedin_automation.js --url="https://www.linkedin.com/feed/" --info=../postinfo.json

let parser = require("minimist");
let fs = require("fs");
let puppeteer = require("puppeteer");

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

    await page.waitForSelector("p.main__sign-in-container > a.main__sign-in-link");  
    await page.click("p.main__sign-in-container > a.main__sign-in-link"); // click on sign in

    await page.waitForTimeout(1500);

    await page.waitForSelector("div.form__input--floating > input#username");  
    await page.type("div.form__input--floating > input#username", info.username, {delay : 50});  // fill login details
    
    await page.waitForSelector("div.form__input--floating > input#password");
    await page.type("div.form__input--floating > input#password", info.password, {delay : 50});

    await page.waitForSelector("div.login__form_action_container > button[type='submit']"); 
    await page.click("div.login__form_action_container > button[type='submit']"); // click on sign in

    await page.waitForSelector("header.msg-overlay-bubble-header > section.msg-overlay-bubble-header__details");  
    await page.click("header.msg-overlay-bubble-header > section.msg-overlay-bubble-header__details"); // minimize the msgs section
    
    await page.waitForTimeout(3000); 

    await page.waitForSelector("div.share-box-feed-entry__closed-share-box  span.artdeco-button__text"); 
    await page.click("div.share-box-feed-entry__closed-share-box  span.artdeco-button__text");  // click on share post section
    
    await page.waitForSelector("button.share-state-change-button__button  span.ph1");  
    await page.click("button.share-state-change-button__button  span.ph1"); // click on check visibility

    await page.waitForTimeout(1000);
 
    await page.waitForSelector("button[data-generic-list-item='ANYONE']");  
    await page.click("button[data-generic-list-item='ANYONE']");   // change visibility to connections onlly
    
    // CONNECTIONS_ONLY
    // ANYONE

    await page.waitForSelector("div.share-box-footer  button.artdeco-button--primary");
    await page.click("div.share-box-footer  button.artdeco-button--primary");  // click on save button

    await page.waitForTimeout(1500);

    await page.keyboard.down('Control')
    await page.keyboard.press('V')   // paste the content
    await page.keyboard.up('Control')

// Had fun working with PUPPETEER Node library 😃 which provides a high-level API to control Chrome or Chromium. 
// ( Most things that we can do manually in the browser can be done using Puppeteer! )

// Here are two web-automated series of steps-
// 1. Hackerrank code submission
// 2. Linkedin post creation
  
    await page.waitForTimeout(2000);

    await page.keyboard.press('Enter')  
    await page.keyboard.press('Enter')

    await page.waitForTimeout(2000);

    await page.waitForSelector("div.share-guider-button-prompt  span.artdeco-button__text"); 
    await page.click("div.share-guider-button-prompt  span.artdeco-button__text");  // click on hashtag

    await page.waitForTimeout(2000);
    await page.keyboard.press('w') 
    await page.keyboard.press('e') 
    await page.keyboard.press('b') 
    await page.keyboard.press('a') 
    await page.keyboard.press('u') 
    await page.keyboard.press('t') 
    await page.waitForTimeout(1000);
    page.keyboard.press('ArrowDown');
    await page.keyboard.press('Enter')  

    await page.waitForTimeout(1000);

    await page.waitForSelector("div.share-guider-button-prompt  span.artdeco-button__text"); 
    await page.click("div.share-guider-button-prompt  span.artdeco-button__text");  // click on hashtag

    await page.waitForTimeout(2000);
    await page.keyboard.press('p') 
    await page.keyboard.press('u') 
    await page.keyboard.press('p') 
    await page.keyboard.press('p') 
    await page.keyboard.press('e') 
    await page.keyboard.press('t')
    await page.keyboard.press('e') 
    await page.waitForTimeout(1000);
    page.keyboard.press('ArrowDown');
    await page.keyboard.press('Enter') 

    await page.waitForTimeout(1500);

    await page.waitForSelector("div.share-box_actions  span.artdeco-button__text"); 
    await page.click("div.share-box_actions  span.artdeco-button__text");  // click on post

    
  }

  init();
