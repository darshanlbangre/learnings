var express = require('express');
var router = express.Router();
var nodemailer = require('nodemailer');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('contact', { title: 'Contact' });
});

router.post('/send',function(req , res, next){
  var transporter = nodemailer.createTransport({
    service:'Gmail',
    auth:{
      user: 'darshan.bangre@mirthcorp.com',
      pass: ''
    }
  });
  
  var options = {
    from: 'Fred Foo ✔ <darshan.bangre@mirthcorp.com>', // sender address
    to: 'darshan.bangre@mirthcorp.com', // list of receivers
    subject: 'Hello ✔', // Subject line
    text: 'Hello world ✔', // plaintext body
    html: '<b>Hello world ✔</b>' // html body  
  };
  
  transporter.sendMail(options, function(err, info){
    if(err){
      console.log(err);
      res.redirect('/');
    }
    else{
      console.log('Message Sent:'+info.response);
      res.redirect('/')
    }
  })
});

module.exports = router;
