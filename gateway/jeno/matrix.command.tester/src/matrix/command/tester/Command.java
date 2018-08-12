package matrix.command.tester;

/*
    public class Command 

    {

        /*

            Array containing the list of all commands

            First : command's name

            Second: command's value

            Third:  help to command

            Forth:  does the command get reply?

             /

        public static Tuple<String, Byte, String, Boolean>[] commands= {

            Tuple.Create<string, byte, string, Boolean>("12V-off-left", 0, "12V kikapcsolasa a bal oldali tablan", false),

            Tuple.Create<string, byte, string, Boolean>("12V-off-right", 1, "12V kikapcsolasa a jobb oldali tablan", false),

            Tuple.Create<string, byte, string, Boolean>("reset-left-panel", 2, "bal oldali panel ujrainditasa", false),

            Tuple.Create<string, byte, string, Boolean>("reset-right-panel", 3, "jobb oldali panel ujrainditasa", false),

            Tuple.Create<string, byte, string, Boolean>("reboot", 4, "MUEB ujrainditasa", false),

            Tuple.Create<string, byte, string, Boolean>("get-status", 5, "statusz lekerdezese", true),

            Tuple.Create<string, byte, string, Boolean>("use-internal-animation", 10, "belso animacio hasznalata", false),

            Tuple.Create<string, byte, string, Boolean>("use-external-animation", 20, "kulso animacio hasznalata", false),

            Tuple.Create<string, byte, string, Boolean>("blank", 30, "tablak elsotetitese", false),

            Tuple.Create<string, byte, string, Boolean>("delete-anim-network-buffer", 6, "halozati buffer torlese", false),

            Tuple.Create<string, byte, string, Boolean>("ping", 0x40, "MUEB fociklus-idejenek meghatarozasa", true)

        };



        /* 

         * Returns the byte value representing a certain command 

         /

        public static byte CommandToByte(string commandToCast)

        {

            byte commandCode = 0;



            for (int i = 0; i < commands.Length; i++)

            {

                if (commands[i].Item1.Equals(commandToCast))

                {

                    commandCode = commands[i].Item2;

                }

            }           



            return commandCode;

        }



        /* 

         * Checks if a given command is one of the valid commands 

         /

        public static Boolean CheckCommandValidity(string commandToCheck)

        {

            for (int i = 0; i < commands.Length; i++)

            {

                if (commandToCheck.Equals(commands[i].Item1))

                {

                    return true;

                }

            }

            return false;

        }

    };

*/