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
            if ('username' in newMessage) {
                if (typeof newMessage.username === 'string') {
                    newMessage.username = sanitizeHTML(newMessage.username);
                }
            }
            if ('text' in newMessage) {
                if (typeof newMessage.text === 'string') {
                    newMessage.text = sanitizeHTML(newMessage.text);
                }
            }
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
        if (typeof username === 'string') {
            Message.find({username: username}, callback)
        } else {
            var e = new Error('Username must be of type String');
            callback(e);
        }
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
