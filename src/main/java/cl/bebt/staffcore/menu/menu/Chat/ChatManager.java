package cl.bebt.staffcore.menu.menu.Chat;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.menu.MenuC;
import cl.bebt.staffcore.menu.PlayerMenuUtility;
import cl.bebt.staffcore.menu.menu.Others.ServerManager;
import cl.bebt.staffcore.sql.SQLGetter;
import cl.bebt.staffcore.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ChatManager extends MenuC {
    
    
    private final main plugin;
    
    public ChatManager( PlayerMenuUtility playerMenuUtility , main plugin ){
        super( playerMenuUtility );
        this.plugin = plugin;
    }
    
    public String getMenuName( ){
        return utils.chat( "&cChat Manager" );
    }
    
    public int getSlots( ){
        return 45;
    }
    
    public void handleMenu( InventoryClickEvent e ){
        Player p = ( Player ) e.getWhoClicked( );
        PersistentDataContainer PlayerData = p.getPersistentDataContainer( );
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( this.plugin , "mcmanager" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            (new MuteChatManager( main.getPlayerMenuUtility( p ) , this.plugin )).open( p );
            p.updateInventory( );
            e.setCancelled( true );
        } else if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( this.plugin , "panel" ) , PersistentDataType.STRING ) ) {
            e.setCancelled( true );
        } else if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( this.plugin , "TStaffOn" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            PlayerData.remove( new NamespacedKey( this.plugin , "staffchat" ) );
            if ( utils.mysqlEnabled( ) )
                SQLGetter.set( p.getName( ) , "staffchat" , "false" );
            utils.tell( p , "&8[&3&lSC&r&8]&r &cOff" );
            (new ChatManager( main.getPlayerMenuUtility( p ) , this.plugin )).open( p );
            p.updateInventory( );
            e.setCancelled( true );
        } else if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( this.plugin , "TStaffOff" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            PlayerData.set( new NamespacedKey( this.plugin , "staffchat" ) , PersistentDataType.STRING , "staffchat" );
            if ( utils.mysqlEnabled( ) )
                SQLGetter.set( p.getName( ) , "staffchat" , "true" );
            utils.tell( p , "&8[&3&lSC&r&8]&r &aOn" );
            (new ChatManager( main.getPlayerMenuUtility( p ) , this.plugin )).open( p );
            p.updateInventory( );
            e.setCancelled( true );
        } else if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( this.plugin , "ccmanager" ) , PersistentDataType.STRING ) ) {
            p.closeInventory( );
            (new ChatSettings( main.getPlayerMenuUtility( p ) , this.plugin )).open( p );
            e.setCancelled( true );
        } else if ( e.getCurrentItem( ).getType( ).equals( Material.BARRIER ) ) {
            if ( e.getClick( ).isLeftClick( ) ) {
                p.closeInventory( );
                (new ServerManager( main.getPlayerMenuUtility( p ) , this.plugin )).open( p );
                e.setCancelled( true );
            } else if ( e.getClick( ).isRightClick( ) ) {
                p.closeInventory( );
            }
        }
    }
    
    public void setMenuItemsPlayer( Player p ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack ClearChat = new ItemStack( Material.PAPER , 1 );
        ItemStack MuteChat = new ItemStack( Material.MAP , 1 );
        ItemMeta metaTChat = ClearChat.getItemMeta( );
        ItemMeta metaMChat = MuteChat.getItemMeta( );
        metaTChat.setDisplayName( utils.chat( "&cClear Chat Manager" ) );
        metaMChat.setDisplayName( utils.chat( "&5Mute Chat Manager" ) );
        lore.add( utils.chat( "&7Click to open the &dClear Chat Manager." ) );
        metaTChat.setLore( lore );
        lore.clear( );
        lore.add( utils.chat( "&7Click to open the &dMute Chat Manager." ) );
        metaMChat.setLore( lore );
        metaTChat.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "ccmanager" ) , PersistentDataType.STRING , "ccmanager" );
        metaMChat.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "mcmanager" ) , PersistentDataType.STRING , "mcmanager" );
        ClearChat.setItemMeta( metaTChat );
        MuteChat.setItemMeta( metaMChat );
        int i;
        for ( i = 0; i < 10; i++ ) {
            if ( this.inventory.getItem( i ) == null )
                this.inventory.setItem( i , bluePanel( ) );
        }
        for ( i = 10; i < 17; i++ ) {
            if ( this.inventory.getItem( i ) == null )
                this.inventory.setItem( i , redPanel( ) );
        }
        this.inventory.setItem( 17 , bluePanel( ) );
        this.inventory.setItem( 18 , bluePanel( ) );
        this.inventory.setItem( 19 , redPanel( ) );
        this.inventory.setItem( 25 , redPanel( ) );
        this.inventory.setItem( 26 , bluePanel( ) );
        this.inventory.setItem( 27 , bluePanel( ) );
        for ( i = 28; i < 35; i++ ) {
            if ( this.inventory.getItem( i ) == null )
                this.inventory.setItem( i , redPanel( ) );
        }
        for ( i = 35; i < 45; i++ ) {
            if ( this.inventory.getItem( i ) == null )
                this.inventory.setItem( i , bluePanel( ) );
        }
        this.inventory.setItem( 20 , redPanel( ) );
        this.inventory.setItem( 21 , MuteChat );
        this.inventory.setItem( 22 , redPanel( ) );
        this.inventory.setItem( 23 , ClearChat );
        this.inventory.setItem( 24 , redPanel( ) );
        this.inventory.setItem( 25 , redPanel( ) );
        this.inventory.setItem( 31 , makeItem( Material.BARRIER , ChatColor.DARK_RED + "Close" ) );
    }
}
