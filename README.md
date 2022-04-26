# Welcome to SeTi-Server development

The server is almost halfway done so I will continue from the work which is left.


**Chat Management**
1. Make up a ChannelManager class.
    The class will have a Array of `Channel` class which will store all channels.
    > Channel class is already made
    It will have `saveChannel()` which will save the Channel array to files.
    It will have `addMessage(String channelId,String message, String author)`
    It will have `removeMessage(String channelId,String messageId)`
    
