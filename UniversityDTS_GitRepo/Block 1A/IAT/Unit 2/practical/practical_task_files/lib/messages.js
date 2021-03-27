const sanitizeHTML = require('sanitize-html');

module.exports = function(url,callback){
  const mongoose = require('mongoose');
  mongoose.set('useNewUrlParser', true);
  mongoose.set('useUnifiedTopology', true);
  mongoose.set('useFindAndModify', false);
  mongoose.connect(url,callback);

  const Message = mongoose.model(
    'messages',
    {}
  );

  return {
    create:function(newMessage,callback){
      callback();
    },
    read:function(id,callback){
      callback();
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
