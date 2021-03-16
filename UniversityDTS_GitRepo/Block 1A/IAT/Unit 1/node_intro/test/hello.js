const chai = require ('chai');
const expect = chai.expect;
const request = require('superagent');
const status = require('http-status');

const targetUrl = 'http://localhost:3000/';

describe('Hello World API', function(){
  var server;

  before(function(done){
    var express = require('express');
    var app = express();
    var router = require('../routes/hello.js');

    app.use('/',router);

    var port = 3000;
    server = app.listen(port,function(){
      done();
    });
  });

  it('GET request responds with a status of 200 and the text "Hello, World!"', function(done){
    request.get(targetUrl)
    .end(function(err,res){
      expect(err).to.not.be.an('error');
      expect(res.statusCode).to.equal(status.OK);
      expect(res.text).to.equal('Hello, World!');
      done();
    });
  });

  it('POST request responds with a status of 405', function(done){
    request.post(targetUrl)
    .end(function(err,res){
      expect(err).to.be.an('error');
      expect(res.statusCode).to.equal(status.METHOD_NOT_ALLOWED);
      done();
    });
  });

  after(function(done){
    server.close();
    done();
  });

});
