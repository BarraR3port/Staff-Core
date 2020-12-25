package cl.bebt.staffcore.menu.menu.Warn;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.menu.PlayerMenuUtility;
import cl.bebt.staffcore.menu.WarnPlayerMenu;
import cl.bebt.staffcore.utils.utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class WarnMenu extends WarnPlayerMenu{
    private final main plugin;

    public WarnMenu( PlayerMenuUtility playerMenuUtility , main plugin , String p2 ){
        super( playerMenuUtility );
        this.plugin = plugin;
        this.warned = p2;
    }

    @Override
    public String getMenuName( ){
        return utils.chat( "&cWarn &4" + warned );
    }

    @Override
    public int getSlots( ){
        return 54;
    }


    @Override
    public void handleMenu( InventoryClickEvent e ){
        Player p = ( Player ) e.getWhoClicked( );
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "hacking" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "Hacking" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "killaura" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "KillAura" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "flying" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "Flying" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "speed" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "Speed" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "griefing" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "Griefing" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "spamming" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "Spamming" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "bhop" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            new WarnTimeChose( playerMenuUtility , plugin , p , warned , "BunnyHop" ).open( p );
        }
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "other" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            PersistentDataContainer PlayerData = p.getPersistentDataContainer( );
            PlayerData.set( new NamespacedKey( main.plugin , "warnmsg" ) , PersistentDataType.STRING , warned );
            utils.tell( p , plugin.getConfig( ).getString( "server_prefix" ) + plugin.getConfig( ).getString( "staff.other_reason" ) );
        } else if ( e.getCurrentItem( ).getType( ).equals( Material.BARRIER ) ) {

        }
    }

    public ItemStack hacking( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.RED_DYE );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor hacking" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Hacking" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "hacking" ) , PersistentDataType.STRING , "hacking" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack killaura( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.DIAMOND_SWORD );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor KillAura" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4KillAura" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "killaura" ) , PersistentDataType.STRING , "killaura" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack flying( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.FEATHER );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor Flying" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Flying" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "flying" ) , PersistentDataType.STRING , "flying" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack speed( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.SUGAR );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor Hacking" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Speed" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "speed" ) , PersistentDataType.STRING , "speed" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack spamming( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.PAPER );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor Spamming" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Spamming" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "spamming" ) , PersistentDataType.STRING , "spamming" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack griefing( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.TNT );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor Griefing" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Griefing" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "griefing" ) , PersistentDataType.STRING , "griefing" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack bhop( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.RABBIT_HIDE );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor BunnyHop" ) );
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4BunnyHop" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "bhop" ) , PersistentDataType.STRING , "bhop" );
        item.setItemMeta( meta );
        return item;
    }

    public ItemStack other( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack item = new ItemStack( Material.FLOWER_BANNER_PATTERN );
        ItemMeta meta = item.getItemMeta( );
        lore.add( utils.chat( "&dWarn &r" + warned + " &dfor other reason." ) );
        lore.add( utils.chat( "&dType in chat the reason." ) );
        assert meta != null;
        meta.setLore( lore );
        meta.setDisplayName( utils.chat( "&4Other reason" ) );
        meta.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "other" ) , PersistentDataType.STRING , "other" );
        item.setItemMeta( meta );
        return item;
    }

    @Override
    public void setMenuItems( ){
        addMenuBorder( );
        inventory.addItem( hacking( ) );
        inventory.addItem( killaura( ) );
        inventory.addItem( flying( ) );
        inventory.addItem( speed( ) );
        inventory.addItem( griefing( ) );
        inventory.addItem( spamming( ) );
        inventory.addItem( bhop( ) );
        inventory.addItem( other( ) );
    }
}
