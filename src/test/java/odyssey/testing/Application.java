package odyssey.testing;

import odyssey.Odyssey;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Application {

    public static void main(String[] args) {

        Odyssey<MineriftProfile> library = Odyssey.getLibrary();

        library.registerProfile(null);

    }

}
