package gusto.fatec.processos.model;

public enum OSEnum {

    WINDOWS {
        @Override
        public String listarProcessos() {
            return "TASKLIST /FO TABLE";
        }

        @Override
        public String matarPID() {
            return "TASKKILL /PID ";
        }

        @Override
        public String matarNome() {
            return "TASKKILL /IM ";
        }
    },

    LINUX {
        @Override
        public String listarProcessos() {
            return "ps -A";
        }

        @Override
        public String matarPID() {
            return "kill -9 ";
        }

        @Override
        public String matarNome() {
            return "killall -9 ";
        }
    };

    public abstract String listarProcessos();

    public abstract String matarPID();

    public abstract String matarNome();

    public static OSEnum getOS() {
        if (System.getProperty("os.name").contains("Windows")) {
            return WINDOWS;
        }

        return LINUX;
    }
}
