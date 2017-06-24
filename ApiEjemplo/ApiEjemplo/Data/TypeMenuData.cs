using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TypeMenuData
    {
        public static void insert(TypeMenu tMenu)
        {
            string sInsert = "Insert into type_menu (name,idBranchRestaurant) values ('" + tMenu.name + "'" +tMenu.idBranchRestaurant + ")";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TypeMenu tMenu)
        {
            string sUpdate = "update type_menu set name='" + tMenu.name + "',idBranchRestaurant="+ tMenu.idBranchRestaurant + " where idTypeMenu=" + Convert.ToString(tMenu.idTypeMenu);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from type_menu where idTypeMenu=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static TypeMenu getById(int id)
        {
            string select = "select * from type_menu where idTypeMenu=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TypeMenu tMenu;
            if (dt.Rows.Count > 0)
            {
                tMenu = getByRow(dt.Rows[0]);
                return tMenu;
            }
            return null;
        }

        public static List<TypeMenu> getAll()
        {
            string select = "select * from type_menu";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeMenu> list = new List<TypeMenu>();
            TypeMenu tMenu;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tMenu = getByRow(row);
                    list.Add(tMenu);
                }
                tMenu = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<TypeMenu> getMenuByBranch(int id)
        {
            string select = "select * from type_menu where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeMenu> list = new List<TypeMenu>();
            TypeMenu tMenu;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tMenu = getByRow(row);
                    list.Add(tMenu);
                }
                tMenu = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TypeMenu getByRow(DataRow row)
        {
            TypeMenu tMenu = new TypeMenu();
            tMenu.idTypeMenu = row.Field<int>("idTypeMenu");
            tMenu.name = row.Field<string>("name");
            tMenu.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            return tMenu;
        }
    }
}