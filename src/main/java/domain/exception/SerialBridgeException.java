package domain.exception;

public class SerialBridgeException extends IllegalStateException {

    public SerialBridgeException() {
        super("연속된 다리가 생성되었습니다");
    }
}
