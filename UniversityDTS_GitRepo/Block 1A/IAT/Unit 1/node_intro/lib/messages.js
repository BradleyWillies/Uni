const mongoose = require('mongoose');
const sanitizeHTML = require('sanitize-html');
mongoose.set('useNewUrlParser', true);
mongoose.set('useUnifiedTopology', true);
mongoose.set('useFindAndModify', false);

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

module.exports = function(url,callback){
    mongoose.connect(url, callback);

        return {
            create:function(newMessage,callback){
            try {
                if ('username' in newMessage && typeof newMessage.username === 'string'
                    && 'text' in newMessage && typeof newMessage.text === 'string') {
                        newMessage.username = sanitizeHTML(newMessage.username);
                        newMessage.text = sanitizeHTML(newMessage.text);
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
                callback(new Error('Username must be of type String'));
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
