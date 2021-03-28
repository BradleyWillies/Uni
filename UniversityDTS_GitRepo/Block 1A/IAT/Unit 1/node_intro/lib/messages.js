const sanitizeHTML = require('sanitize-html');

module.exports = function(url,callback){
  const mongoose = require('mongoose');
  mongoose.set('useNewUrlParser', true);
  mongoose.set('useUnifiedTopology', true);
  mongoose.set('useFindAndModify', false);
  mongoose.connect(url,callback);
  mongoose.connect('mongodb://localhost:27017/test');

  const Message = mongoose.model(
    'messages',
    { username: String, text: String }
  );

  return {
    create:function(newMessage,callback){
        var message = new Message(newMessage);
        message.save(callback);
    },
    read:function(id,callback){
      Message.findById(id, callback);
    },
    readUsername:function(username,callback){
      callback();
    },
    readAll:function(callback){
      callback();
    },
    update:function(id,updatedMessage,callback){
      callback();
    },
    delete:function(id,callback){
      callback();
    },
    deleteAll:function(callback){
      Message.deleteMany({},callback);
    },
    disconnect:function(){
      mongoose.disconnect();
    }
  };
};
