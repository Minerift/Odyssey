package odyssey.testing;

import odyssey.Odyssey;

public class Application {

    public static void main(String[] args) {

        Odyssey<MineriftProfile> lib = Odyssey.create();

        MineriftProfile profile = new MineriftProfile();
        lib.registerProfile(profile);

    }

}
