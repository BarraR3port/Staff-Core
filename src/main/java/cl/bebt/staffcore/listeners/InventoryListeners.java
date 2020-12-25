package cl.bebt.staffcore.listeners;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.menu.InventoryMenu;
import cl.bebt.staffcore.utils.Items;
import cl.bebt.staffcore.utils.OpenEnderSee;
import cl.bebt.staffcore.utils.OpenInvSee;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class InventoryListeners implements Listener{

    private final main plugin;

    public InventoryListeners( main plugin ){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEnchant( EnchantItemEvent e ){
        if ( main.invSee.containsValue( e.getEnchanter( ) ) ) {
            Player target = e.getEnchanter( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getEnchanter( ) ) ) {
            Player target = e.getEnchanter( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onClose( InventoryCloseEvent e ){
        InventoryHolder holder = e.getInventory( ).getHolder( );
        if ( holder instanceof InventoryMenu ) {
            InventoryMenu menu = ( InventoryMenu ) holder;
            menu.handleMenu( e );
        }

    }

    @EventHandler
    public void onMenuClick( InventoryClickEvent e ){
        if ( main.invSee.containsValue( e.getWhoClicked( ) ) ) {
            ItemStack item = e.getCurrentItem( );
            ItemStack cursor = e.getCursor( );
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenInvSee.getOwner( target );
            try {
                if ( item.equals( Material.AIR ) ) {
                    // player put item to inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 2L );
                } else if ( !item.equals( Material.AIR ) && cursor.getType( ).equals( Material.AIR ) ) {
                    // player take item from inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 4L );
                } else if ( !item.equals( Material.AIR ) && !cursor.getType( ).equals( Material.AIR ) ) {
                    // player swap item in inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
                }
            } catch ( NullPointerException error ) {
                Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
            }
        }
        if ( main.enderSee.containsValue( e.getWhoClicked( ) ) ) {
            ItemStack item = e.getCurrentItem( );
            ItemStack cursor = e.getCursor( );
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenEnderSee.getOwner( target );
            try {
                if ( item.equals( Material.AIR ) ) {
                    // player put item to inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 2L );
                } else if ( !item.equals( Material.AIR ) && cursor.getType( ).equals( Material.AIR ) ) {
                    // player take item from inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 4L );
                } else if ( !item.equals( Material.AIR ) && !cursor.getType( ).equals( Material.AIR ) ) {
                    // player swap item in inventory
                    Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
                }
            } catch ( NullPointerException error ) {
                Bukkit.getScheduler( ).scheduleSyncDelayedTask( main.plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
            }
        }
        PersistentDataContainer PlayerData = e.getWhoClicked( ).getPersistentDataContainer( );
        if ( PlayerData.has( new NamespacedKey( plugin , "frozen" ) , PersistentDataType.STRING ) ) {
            e.setCancelled( true );
        }
        if ( PlayerData.has( new NamespacedKey( plugin , "vanished" ) , PersistentDataType.STRING ) ||
                PlayerData.has( new NamespacedKey( plugin , "frozen" ) , PersistentDataType.STRING ) ) {
            if ( e.getClick( ) == ClickType.CONTROL_DROP || e.getClick( ) == ClickType.DROP || e.getClick( ) == ClickType.CREATIVE ) {
                e.setCancelled( true );
            }
        }
    }

    @EventHandler
    public void onInteract( InventoryInteractEvent e ){
        if ( main.invSee.containsValue( e.getWhoClicked( ) ) ) {
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getWhoClicked( ) ) ) {
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( InventoryDragEvent e ){
        InventoryHolder holder = e.getInventory( ).getHolder( );
        if ( holder instanceof InventoryMenu ) {
            InventoryMenu menu = ( InventoryMenu ) holder;
            menu.handleMenu( e );
        }
        if ( main.invSee.containsValue( e.getWhoClicked( ) ) ) {
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getWhoClicked( ) ) ) {
            Player target = ( Player ) e.getWhoClicked( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerDropItemEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( EntityPickupItemEvent e ){
        if ( e.getEntity( ) instanceof Player ) {
            if ( main.invSee.containsValue( e.getEntity( ) ) ) {
                Player target = ( Player ) e.getEntity( );
                Player p = OpenInvSee.getOwner( target );
                Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
            }
        }
        if ( main.enderSee.containsValue( e.getEntity( ) ) ) {
            Player target = ( Player ) e.getEntity( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerItemConsumeEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerItemBreakEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerCommandPreprocessEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerBucketFillEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerItemDamageEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerInteractAtEntityEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerInteractEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerDeathEvent e ){
        if ( main.invSee.containsValue( e.getEntity( ) ) ) {
            Player target = e.getEntity( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getEntity( ) ) ) {
            Player target = e.getEntity( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerRespawnEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenInvSee.updateInventory( p , target ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void onInventory( PlayerMoveEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> p.getOpenInventory( ).getTopInventory( ).setItem( 31 , Items.head( target ) ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void oItemHeld( PlayerItemHeldEvent e ){
        if ( main.invSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenInvSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> p.getOpenInventory( ).getTopInventory( ).setItem( 30 , target.getInventory( ).getItemInMainHand( ) ) , 5L );
        }
        if ( main.enderSee.containsValue( e.getPlayer( ) ) ) {
            Player target = e.getPlayer( );
            Player p = OpenEnderSee.getOwner( target );
            Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
        }
    }

    @EventHandler
    public void oItemHeld( EntityPotionEffectEvent e ){
        if ( e.getEntity( ) instanceof Player ) {
            if ( main.invSee.containsValue( e.getEntity( ) ) ) {
                Player target = ( Player ) e.getEntity( );
                Player p = OpenInvSee.getOwner( target );
                Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> p.getOpenInventory( ).getTopInventory( ).setItem( 28 , Items.potions( target ) ) , 5L );
            }
            if ( main.enderSee.containsValue( e.getEntity( ) ) ) {
                Player target = ( Player ) e.getEntity( );
                Player p = OpenEnderSee.getOwner( target );
                Bukkit.getScheduler( ).scheduleSyncDelayedTask( plugin , ( ) -> OpenEnderSee.updateInventory( p , target ) , 5L );
            }
        }

    }

}
