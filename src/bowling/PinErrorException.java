package bowling;

import java.io.IOException;

public class PinErrorException extends IOException {
    public PinErrorException(int line){
	super("In line "+line+": there is a invalid pin number");
    }
}
