package cl.bebt.staffcore.menu.menu.Chat;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.menu.PaginatedMenu;
import cl.bebt.staffcore.menu.PlayerMenuUtility;
import cl.bebt.staffcore.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Amount extends PaginatedMenu{
    private final main plugin;
    private final Player player;

    public Amount( PlayerMenuUtility playerMenuUtility , main plugin , Player player ){
        super( playerMenuUtility );
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public String getMenuName( ){
        return utils.chat( "&cChose the amount:" );
    }

    @Override
    public int getSlots( ){
        return 54;
    }

    @Override
    public void handleMenu( InventoryClickEvent e ){
        Player p = ( Player ) e.getWhoClicked( );
        int amount = 49;
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "amount" ) , PersistentDataType.INTEGER ) ) {
            p.closeInventory( );
            int yep = e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).get( new NamespacedKey( plugin , "amount" ) , PersistentDataType.INTEGER );
            player.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "amount" ) , PersistentDataType.INTEGER , yep );
            new Quantity( playerMenuUtility , plugin , player ).open( p );
        } else if ( e.getCurrentItem( ).getType( ).equals( Material.BARRIER ) ) {
            p.closeInventory( );
            new MutePlayer( playerMenuUtility , main.plugin ).open( p );
        } else if ( e.getCurrentItem( ).getType( ).equals( Material.DARK_OAK_BUTTON ) ) {
            if ( ChatColor.stripColor( e.getCurrentItem( ).getItemMeta( ).getDisplayName( ) ).equalsIgnoreCase( "Back" ) ) {
                if ( page == 0 ) {
                    p.sendMessage( ChatColor.GRAY + "You are already on the first page." );
                } else {
                    page = page - 1;
                    p.closeInventory( );
                    super.open( p );
                }
            } else if ( ChatColor.stripColor( e.getCurrentItem( ).getItemMeta( ).getDisplayName( ) ).equalsIgnoreCase( "Next" ) ) {
                if ( !((index + 1) >= amount) ) {
                    page = page + 1;
                    p.closeInventory( );
                    super.open( p );
                } else {
                    p.sendMessage( ChatColor.GRAY + "You are on the last page." );
                }
            }
        }
    }

    @Override
    public void setMenuItems( ){
        addMenuBorder( );
        int amount = 49;
        player.getPersistentDataContainer( ).remove( new NamespacedKey( plugin , "seconds" ) );
        player.getPersistentDataContainer( ).remove( new NamespacedKey( plugin , "amount" ) );
        for ( int i = 1; i < getMaxItemsPerPage( ) + 1; i++ ) {
            index = getMaxItemsPerPage( ) * page + i;
            if ( index >= amount ) break;
            //////////////////////////////
            ItemStack clock = new ItemStack( Material.CLOCK , index );
            ItemMeta meta = clock.getItemMeta( );
            meta.setDisplayName( utils.chat( "&a" ) + index );
            meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "amount" ) , PersistentDataType.INTEGER , index );
            clock.setItemMeta( meta );
            inventory.addItem( clock );
            /////////////////////////////
        }


    }
}
