package sugar.free.sightparser.applayer.connection;

import lombok.Getter;
import lombok.Setter;
import sugar.free.sightparser.applayer.AppLayerMessage;
import sugar.free.sightparser.applayer.Service;
import sugar.free.sightparser.error.SightError;
import sugar.free.sightparser.error.UnknownAppErrorCodeError;
import sugar.free.sightparser.pipeline.ByteBuf;

public class ActivateServiceMessage extends AppLayerMessage {

    @Getter
    @Setter
    private byte serviceID;
    @Getter
    @Setter
    private short version;
    @Setter
    private byte[] servicePassword;

    @Override
    public Service getService() {
        return Service.CONNECTION;
    }

    @Override
    public short getCommand() {
        return (short) 0xF7F0;
    }

    @Override
    protected void parse(ByteBuf byteBuf) throws Exception {
        serviceID = byteBuf.readByte();
        version = byteBuf.getShort();
    }

    @Override
    protected byte[] getData() {
        ByteBuf byteBuf = new ByteBuf(19);
        byteBuf.putByte(serviceID);
        byteBuf.putShort(version);
        byteBuf.putBytes(servicePassword);
        return byteBuf.getBytes();
    }
}
