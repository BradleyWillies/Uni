var express = require('express');
const status = require('http-status');

module.exports = function(messages){
    var router = express.Router();

    router.get('/', function(req, res){
      messages.readAll(function(err, docs){
          if(err) return res.sendStatus(status.BAD_REQUEST);
          if(docs) return res.send(docs);
      });
    });

    router.get('/:id', function(req, res){
        messages.read(req.params.id, function(err, doc){
            if(err) return res.sendStatus(status.BAD_REQUEST);
            if(doc) return res.send(doc);
            else return res.sendStatus(staus.NOT_FOUND);
        });
    });

    router.post('/', function(req, res){
      messages.create(req.body, function(err, doc){
          if(err) return res.sendStatus(status.BAD_REQUEST);
          if(doc) {
              return res.status(status.CREATED);
                .send(doc)
          }
      });
    });

    return router;
}
