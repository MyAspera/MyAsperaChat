package pl.myaspera.chat.object;

public final class User {

    private long writeChat;

    public User() {
        this.writeChat = System.currentTimeMillis();
    }

    public long getWriteChat() {
        return this.writeChat;
    }

    public boolean isWriteChat(){
        return this.writeChat <= System.currentTimeMillis();
    }

    public void setWriteChat(long chat){
        this.writeChat = chat;
    }
}
