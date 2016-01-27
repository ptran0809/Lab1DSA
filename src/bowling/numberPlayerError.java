package bowling;

import java.io.IOException;

public class numberPlayerError extends IOException {
    

    public numberPlayerError() {
	super("Invalid Number Of Players");
    }
    
}
