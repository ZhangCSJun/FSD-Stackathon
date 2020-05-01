                           // var http=require("http");
// var server=http.createServer(function(req, res)){
//     if(req.url!=="/favicon.ico"){
//         res.writeHead(200,{"Content-Type":"text/plain", "Access-Control-Allow-Orign":"http://localhost"});
        
//         data = {
            
//         }
//         res.write(data);
//     }
//     res.end();
// }
// server.listen(8080,"localhost",function(){
//     console.log("listening");
// })
var express = require("express");
var app=express();
var bodyParser=require("body-parser");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false})); 

app.all('*', function(req, res, next){
  res.header("Access-Control-Allow-Origin","*");
  res.header("Access-Control-Allow-Headers","Content-Type, Content-Length, Authorization, Accept, X-Requested-With");
  res.header("Access-Contorl_Allow-Headers", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", "3.2.1");
  if(req.method=="OPTIONS") res.sendStatus(200);
  else next();

});

app.post('/login',function(req,res){
  console.log(req.body);
  if(req.body.username=='12345' && req.body.pwd=="123456"){
    res.json({"code":200,"role":"0"});
  }else if(req.body.username=='99999' && req.body.pwd=="123456"){
      res.json({"code":400});
  }else if(req.body.username=='admin' && req.body.pwd=="admin"){
    res.json({"code":200,"role":"1"});
  }else{
    res.json({"code":400});
  }
});

app.get('/getIposDataByCode',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockExchange:'BSE',
      pricePerShare:"$60",
      totalNumberOfShares:"89,000 million",
      openDateTime:"2020-05-13",
      remark:"Profit growth"
    }])
});
app.get('/getIposDataByKeyword',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockExchange:'BSE',
      pricePerShare:"$60",
      totalNumberOfShares:"89,000 million",
      openDateTime:"2020-05-13",
      remark:"Profit growth"
    },{
      companyName:'Tao Bao',
      stockExchange:'NSE',
      pricePerShare:"$58.13",
      totalNumberOfShares:"59,200 million",
      openDateTime:"2020-05-15",
      remark:"Profit growth"
    },{
      companyName:'Jing Dong',
      stockExchange:'NSE',
      pricePerShare:"$38.13",
      totalNumberOfShares:"60,990 million",
      openDateTime:"2020-05-25",
      remark:"Profit growth"
    },{
      companyName:'Old Temple Gold',
      stockExchange:'ASE',
      pricePerShare:"$28.05",
      totalNumberOfShares:"92,000 million",
      openDateTime:"2020-06-01",
      remark:"Profit growth"
    },
  ]);
})

app.get('/getCompanyInfoByCode',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockCode:'180070, 210300',
      stockExchange:'BSE, NSE',
      CEO:"MA YUN",
      BOD:"MA YUN",
      turnover:"$90,900 trillion",
      sector:"E-mall",
      breifwrite:"provide e-shop...",
      status:"active",
    }])
});

app.get('/getCompanyInfoByKeyword',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockCode:'180070, 210300',
      stockExchange:'BSE, NSE',
      CEO:"MA YUN",
      BOD:"MA YUN",
      turnover:"$90,900 trillion",
      sector:"E-mall",
      breifwrite:"provide e-shop...",
      status:"active",
    },{
      companyName:'Tao Bao Market',
      stockCode:'180210',
      stockExchange:'BSE',
      CEO:"MA YUN",
      BOD:"MA YUN",
      turnover:"$30,000 trillion",
      sector:"E-mall",
      breifwrite:"provide e-shop...",
      status:"deactive",
    },{
      companyName:'Jing Dong Market',
      stockCode:'210090',
      stockExchange:'NSE',
      CEO:"Liu Qiang Dong",
      BOD:"Liu Qiang Dong",
      turnover:"$50,000 trillion",
      sector:"E-mall",
      breifwrite:"provide e-shop...",
      status:"active",
    },{
      companyName:'Old Temple Gold',
      stockCode:'380090',
      stockExchange:'ASE',
      CEO:"William",
      BOD:"William",
      turnover:"$68,000 trillion",
      sector:"E-mall",
      breifwrite:"sell jewellery...",
      status:"active",
    }
  ]);
})

app.get('/getCompanyInfoByCode',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockExchange:'BSE',
      pricePerShare:60,
      totalNumberOfShares:89000,
      openDateTime:'2020-05-13',
      remark:'Good Profit',
    }
  ]);
})

app.get('/getIPOsPlannedByKeyword',function(req,res){
  res.json([
    {
      companyName:'Tian Mao Market',
      stockExchange:'BSE',
      pricePerShare:60,
      totalNumberOfShares:89000,
      openDateTime:'2020-05-13',
      remark:'Good Profit',
    },{
      companyName:'Tao Bao Market',
      stockExchange:'NSE',
      pricePerShare:58.13,
      totalNumberOfShares:78000,
      openDateTime:'2020-05-15',
      remark:'Good Profit',
    },{
      companyName:'Jing Dong',
      stockExchange:'NSE',
      pricePerShare:38.58,
      totalNumberOfShares:86900,
      openDateTime:'2020-05-25',
      remark:'Good Profit',
    },{
      companyName:'Old temple gold',
      stockExchange:'ASE',
      pricePerShare:32.8,
      totalNumberOfShares:39900,
      openDateTime:'2020-06-01',
      remark:'Good Profit',
    }
  ]);
})

app.post('/login',function(req,res){
    console.log(req.body);
    if(req.body.oldpwd=='12345'){
      res.json({"code":200});
    }else{
      res.json({"code":400});
    }
})


/*
* Mock get user's profile from service
*/
app.get('/userprofile',function(req,res){
  res.json({
    userName:"william",
    mobileNumber:"(65)831-9588-8818",
    Email:"nightmarewilliam@international.com",
  });
})
/*
* Mock Stock Price Data from Back end
*/
app.get('/stockprice',function(req,res){
  res.json([
    {
      stockCode:'180070',
      stockExchange:'BSE',
      currentPrice:'71.82',
      date:"2020-02-31",
      time:"14:29:30",
    },{
      stockCode:'210300',
      stockExchange:'NSE',
      currentPrice:'68.91',
      date:"2020-02-31",
      time:"14:20:10",
    }
  ])
})

/*
* Mock get companyName by keyword
*/
app.get('/getCompanyNameByKeyword',function(req,res){
  res.json([
    {
      companyCode:'123',
      companyName:'Tian Mao Market'
    },{
      companyCode:'223',
      companyName:'Tian Jin Duck International Group oversea'
    },{
      companyCode:'323',
      companyName:'Tian Hong Drug'
    },
  ])
})
/*
* Mock get companyName by company Code
*/
app.get('/getCompanyNameByCode',function(req,res){
  res.json({
    company:'Tian Mao Mall'
  })
})

/*
* Mock add Stock Exhange Data
*/
app.post('/uploadstockprice',function(req, res){
  res.json({
    code:200,
    msg:"upload success"
  })
})
/*
* Mock add Stock Exhange Data
*/
app.post('/addstockexchange',function(req, res){
  res.json({
    code:200,
    msg:"save success"
  })
})
/*
* Mock delete Stock Exhange Data
*/
app.post('/delstockexchange',function(req, res){
  res.json({
    code:200,
    msg:"delete success"
  })
})
/*
* Mock Stock Exhange Data from Back end
*/
app.get('/stockexchange',function(req,res){
  res.json([
      {
	id:'20103',
        abbrname:'BSE',
        fullname:'Bombay Stock Exchange',
        brief:'Big Exchange',
        contactAddress:'FAC city.ABC road',
        remark:'Since 1980'
      },{
	id:'20104',
        abbrname:'NSE',
        fullname:'National Stock Exchange',
        brief:'Middle Exchange',
        contactAddress:'Fut city.DCO road',
        remark:'Since 1990'
      },{
	id:'30102',
        abbrname:'NYSE',
        fullname:'New York Stock Exchange',
        brief:'Small Exchange',
        contactAddress:'DIY city.GHI road',
        remark:'Since 1993'
      },{
	id:'50802',
        abbrname:'NASDAQ',
        fullname:'National Association of Securities Dealers Automated Quotation',
        brief:'Big',
        contactAddress:'AIC city.NAS road',
        remark:'Since 1996'
      },{
	id:'40302',
        abbrname:'LSE',
        fullname:'London Stock Exchange',
        brief:'Small',
        contactAddress:'AIC city.NAS road',
        remark:'Since 2003'
      },{
	id:'50301',
        abbrname:'TSE',
        fullname:'Tokyo Stock Exchange',
        brief:'In build',
        contactAddress:'Tokyo city.MAIN road',
        remark:'Since 1998'
      },{
	      id:'30702',
        abbrname:'SSE',
        fullname:'Shangshai Stock Exchange',
        brief:'Big One',
        contactAddress:'Shanghai city.NanJing road',
        remark:'Since 1996'
      }
  ]);
})

/*
* Mock add IPOs Plan Data
*/
app.post('/addiposplan',function(req, res){
  res.json({
    code:200,
    msg:"save success"
  })
})

app.post('/getDataSet',function(req, res){

  console.log(req.body.type);
  if(req.body.type=='0'){
    res.json({
      dimension:['product', '1Q', '2Q', '3Q', '4Q'],
      production:[{product: 'Tian Mao Mall', '1Q': 43.3, '2Q': 85.8, '3Q': 93.7, '4Q': 63.9}],
      series:[{type: 'bar'},{type: 'bar'},{type: 'bar'},{type: 'bar'}]
    }) 
  }else if(req.body.type=='1'){

    if(req.body.comapnyCode.length==3){
      if(req.body.period = '1Q'){
        res.json({
          dimension:['product', 'Jun', 'Feb', 'Mar'],
          production:[
            {product: 'Tian Mao Mall', 'Jun': 43.3, 'Feb': 85.8, 'Mar': 93.7},
            {product: 'Jing Dong Mall', 'Jun': 83.1, 'Feb': 73.4, 'Mar': 55.1},
            {product: 'Tao Bao', 'Jun': 86.4, 'Feb': 65.2, 'Mar': 82.5},
          ],
          series:[{type: 'bar'},{type: 'bar'},{type: 'bar'}]
        }) 
      }else if(req.body.period = '2Q'){
        res.json({
          dimension:['product', 'Apr', 'May', 'Jun'],
          production:[
            {product: 'Tian Mao Mall', 'Apr': 53.3, 'May': 55.8, 'Jun': 63.7},
            {product: 'Jing Dong Mall', 'Apr': 43.1, 'May': 83.4, 'Jun': 75.1},
            {product: 'Tao Bao', 'Apr': 90.4, 'May': 79.2, 'Jun': 59.5},
          ],
          series:[{type: 'bar'},{type: 'bar'},{type: 'bar'}]
        }) 
      }else if(req.body.period = '3Q'){
        res.json({
          dimension:['product', 'Jul', 'Aug', 'Sep'],
          production:[
            {product: 'Tian Mao Mall', 'Jul': 73.3, 'Aug': 85.8, 'Sep': 93.7},
            {product: 'Jing Dong Mall', 'Jul': 53.1, 'Aug': 83.4, 'Sep': 65.1},
            {product: 'Tao Bao', 'Jul': 56.4, 'Aug': 77.2, 'Sep': 87.5},
          ],
          series:[{type: 'bar'},{type: 'bar'},{type: 'bar'}]
        }) 
      }else if(req.body.period = '4Q'){
        res.json({
          dimension:['product', 'Oct', 'Nov', 'Dec'],
          production:[
            {product: 'Tian Mao Mall', 'Oct': 88.3, 'Nov': 80.8, 'Dec': 73.7},
            {product: 'Jing Dong Mall', 'Oct': 67.1, 'Nov': 53.4, 'Dec': 75.1},
            {product: 'Tao Bao', 'Oct': 79.4, 'Nov': 90.2, 'Dec': 110.5},
          ],
          series:[{type: 'bar'},{type: 'bar'},{type: 'bar'}]
        }) 
      }
    } else if(req.body.comapnyCode.length==2){
        if(req.body.period = '1Q'){
          res.json({
            dimension:['product', 'Jun', 'Feb', 'Mar'],
            production:[
              {product: 'Tian Mao Mall', 'Jun': 43.3, 'Feb': 85.8, 'Mar': 93.7},
              {product: 'Jing Dong Mall', 'Jun': 83.1, 'Feb': 73.4, 'Mar': 55.1},
            ],
            series:[{type: 'bar'},{type: 'bar'}]
          }) 
        }else if(req.body.period = '2Q'){
          res.json({
            dimension:['product', 'Apr', 'May', 'Jun'],
            production:[
              {product: 'Tian Mao Mall', 'Apr': 53.3, 'May': 55.8, 'Jun': 63.7},
              {product: 'Jing Dong Mall', 'Apr': 43.1, 'May': 83.4, 'Jun': 75.1},
            ],
            series:[{type: 'bar'},{type: 'bar'}]
          }) 
        }else if(req.body.period = '3Q'){
          res.json({
            dimension:['product', 'Jul', 'Aug', 'Sep'],
            production:[
              {product: 'Tian Mao Mall', 'Jul': 73.3, 'Aug': 85.8, 'Sep': 93.7},
              {product: 'Jing Dong Mall', 'Jul': 53.1, 'Aug': 83.4, 'Sep': 65.1},
            ],
            series:[{type: 'bar'},{type: 'bar'}]
          }) 
        }else if(req.body.period = '4Q'){
          res.json({
            dimension:['product', 'Oct', 'Nov', 'Dec'],
            production:[
              {product: 'Tian Mao Mall', 'Oct': 88.3, 'Nov': 80.8, 'Dec': 73.7},
              {product: 'Jing Dong Mall', 'Oct': 67.1, 'Nov': 53.4, 'Dec': 75.1},
            ],
            series:[{type: 'bar'},{type: 'bar'}]
          }) 
        }
    }

  }

})


app.listen('3000','127.0.0.1');