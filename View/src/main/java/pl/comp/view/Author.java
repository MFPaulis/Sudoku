
package pl.comp.view;

import java.util.ListResourceBundle;

public class Author extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
          {"229995", "Paulina Sidor"},
          {"236690", "Dorota Wiechno"}
        };
    }
}
