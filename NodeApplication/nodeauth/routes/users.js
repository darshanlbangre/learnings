var express = require('express');
var router = express.Router();
var User = require('../models/user');
var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;

/* GET users listing. */
router.get('/', function (req, res, next) {
  res.send('respond with a resource');
});

router.get('/login', function (req, res, next) {
  res.render('login', {
    'title': 'LogIn'
  });
});


passport.serializeUser(function(user, done) {
  done(null, user.id);
});

passport.deserializeUser(function(id, done) {
  User.getUserById(id, function(err, user) {
    done(err, user);
  });
});

passport.use(new LocalStrategy(
  function(username,password,done){
    User.getUserByUserName(username,function(err,user){
      if(err) throw err;
      
      if(!user){
        console.log('Invalid User');
        return done(null,false,{message:'Invalid User'});
      }
      
      User.comparePassword(password, user.password,function(err,isMatch){
        if(err) throw err;
        
        if(isMatch){
          return  done(null,user);
        }else{
          console.log('Invalid Password');
          return done(null,false,{message:'Invalid Password'});
        }
      });
    });
  }
))

  router.post('/login', passport.authenticate('local', {
  failureRedirect: 'users/login',
  failureFlash: 'Invalid UserName/Password'
}), function (req, res) {
  console.log('Auth Success');
  req.flash('Success','You are logged in');
  res.render('member', {
     username: req.body.username
   });
 
});

router.post('/register', function (req, res, next) {

  var name = req.body.username;
  var email = req.body.email;
  var password = req.body.password;
  var password2 = req.body.password2;

  req.checkBody('username', 'Name is required').notEmpty();
  req.checkBody('email', 'Email is required').notEmpty();
  req.checkBody('email', 'Invalid email').isEmail();


  var errors = req.validationErrors();

  if (errors) {
    res.render('index', {
      errors: errors,
      name: name,
      email: email,
      password: password,
      password2: password2
    })
  } else {
    var newUser = new User({
      name: name,
      email: email,
      password: password
    });
  }

  User.createUser(newUser, function (err, user) {
    if (err) throw err;
    console.log(user);

  });

  req.flash('success', 'You are now registered');

  res.location('/');
  res.redirect('/');

});

module.exports = router;
