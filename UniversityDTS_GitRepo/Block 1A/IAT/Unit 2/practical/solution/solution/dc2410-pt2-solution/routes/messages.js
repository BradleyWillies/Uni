const express = require('express');
const status  = require('http-status');

module.exports = function(messages){
  var router = express.Router();

  router.get('/',function(req,res){
    messages.readAll(function(err,docs){
      res.send(docs);
    });
  });

  router.get('/:id',function(req,res){
    messages.read(req.params.id,function(err,doc){
      res.send(doc);
    });
  });

  router.post('/',function(req,res){
    messages.create(req.body,function(err,doc){
      return res.status(status.CREATED)
        .send(doc);
    });
  });

  return router;
};
