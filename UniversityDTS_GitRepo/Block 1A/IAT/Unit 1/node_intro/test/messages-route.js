const bodyParser = require('body-parser');
const chai       = require('chai');
const expect     = chai.expect;
const request = require('superagent');
const status  = require('http-status');

const config = {
  port: 3000,
  db: {
    url:'mongodb://localhost:27017/test'
  }
};

const endpoint = '/api/v1/messages/';
const apiRoot = 'http://localhost:' + config.port + endpoint;

describe('api/v1/messages/ endpoint', function() {
    var server;
    var messages;

    const validMessages = [
        {
            username:'Alice',
            text:'Alice\'s Message'
        },
        {
            username:'Bob',
            text:'Bob\'s Message'
          }
      ];

      before(function(done){
         messages = require('../lib/messages.js')(
            config.db.url,
            function(err){
                if(err) return done(new Error(err));
                const express = require('express');
                var app = express();
                app.use(bodyparser.json());
                app.use(endpoint, require('../routes/messages')(messages));
                server = app.listen(config.port, function(){done();});
            }
         );
      });

      beforeEach(function(done){
         messages.deleteAll(function(err){
                if(err) return done(new Error(err));
                done();
         });
      });

      after(function(){
         messages.disconnect();
         server.close();
      });

      it('GET request to messages/ gets all messages', function(done){
          const MSG_1_IDX = 0;
          const MSG_2_IDX = 1;

          done();
      });
});
