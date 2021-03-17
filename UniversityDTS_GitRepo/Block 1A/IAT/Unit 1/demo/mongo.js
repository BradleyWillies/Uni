const MongoClient = require('mongodb').MongoClient;
var url = 'mongodb://localhost:27017/';

MongoClient.connect(url,{ useNewUrlParser: true, useUnifiedTopology: true},
  function(err,client){
    const db = client.db('demo');           // selecting or creating the 'demo' db
    db.collection('demodata').insertOne(    // access the 'demodata' table or collection
      {Hello:'world'},                      // inserting an id of hello and a value of world
      function(err,result){                  // anonymous callback
        db.collection('demodata').findOne(  // when above completes find a record from the demodata table
          {},
          function(err,result){
            console.log(result);            // when above successful output result
            client.close();
          });
      });
});
