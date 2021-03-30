module.exports = function(url,callback){
  const mongoose = require('mongoose');
  const sanitizeHTML = require('sanitize-html');
  mongoose.set('useNewUrlParser', true);
  mongoose.set('useUnifiedTopology', true);
  mongoose.set('useFindAndModify', false);
  mongoose.connect(url,callback);

  const credentialsSchema = new mongoose.Schema(
      {
            username: { type: String, required: true },
            text: { type: String, required: true }
      },
      {strict: 'throw'}
  );

  const Message = mongoose.model(
    'messages',
    credentialsSchema
  );

  return {
    create:function(newMessage,callback){
        try {
            // var username = sanitizeHTML(newMessage.username);
            // var text = sanitizeHTML(newMessage.text);
            // var sanitizedMessage = {username:username, text:text};
            // var message = new Message(sanitizedMessage);
            var message = new Message(newMessage);
            message.save(callback);
        } catch (e) {
            callback(e);
        }
    },
    read:function(id,callback){
      Message.findById(id, callback);
    },
    readUsername:function(username,callback){
      Message.find({username: username}, callback)
    },
    readAll:function(callback){
      Message.find({}, callback);
    },
    update:function(id,updatedMessage,callback){
      Message.findByIdAndUpdate(id, updatedMessage, callback);
    },
    delete:function(id,callback){
      Message.findByIdAndRemove(id, callback);
    },
    deleteAll:function(callback){
      Message.deleteMany({}, callback);
    },
    disconnect:function(){
      mongoose.disconnect();
    }
  };
};
