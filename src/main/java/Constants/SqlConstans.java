package Constants;

public class SqlConstans {
        public static final String
                DELETE_FROM_T_ORDERS = "delete from s11dev.courierDS_test.dbo.t_orders",
                DELETE_FROM_T_OREDERS_PARTNER = "delete from s11dev.courierDS_test.dbo.t_orders_partner",
                DELETE_FROM_T_OREDERS_PRODUCT = "delete from s11dev.courierDS_test.dbo.t_orders_products",
                INSERT_INTO_T_ORDERS = "insert into s11dev.courierDS_test.dbo.t_orders " +
                        "select * from t_orders (nolock) where dt>=getdate()-0.05",
                INSERT_INTO_T_OREDERS_PARTNER = "insert into s11dev.courierDS_test.dbo.t_orders_partner " +
                        "select * from t_orders_partner (nolock) " +
                        "where id in(select id from t_orders (nolock) where dt>=getdate()-0.05)",
                INSERT_INTO_T_OREDERS_PRODUCT = "insert into s11dev.courierDS_test.dbo.t_orders_products " +
                        "select * from t_orders_products (nolock) " +
                        "where id_order in(select id from t_orders (nolock) where dt>=getdate()-0.05)",
                UPDATE_T_ORDERS = "update s11dev.courierDS_test.dbo.t_orders set status=0",
                UPDATE_T_ORDERS_PARTNER = "update s11dev.courierDS_test.dbo.t_orders_partner set status=0";
}
