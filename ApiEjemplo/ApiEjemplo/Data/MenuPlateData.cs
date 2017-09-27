using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class MenuPlateData
    {
     /*   public static void insert(MenuPlate menuPlate)
        {
            string[] row = new string[] { "idMenuPlate", "idPlateMenu", "idTypeMenu" };
            object[] values = new object[] { menuPlate.idPlateMenu, menuPlate.idPlateMenu, menuPlate.idTypeMenu };
            string sInsert = QueryHelper.insert("menu_plate", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
       */
        
        public static int newinsert(MenuPlate menuPlate)
        {
            string[] row = new string[] { "idMenuPlate", "idPlateMenu", "idTypeMenu" };
            object[] values = new object[] { menuPlate.idPlateMenu, menuPlate.idPlateMenu, menuPlate.idTypeMenu };
            string sInsert = QueryHelper.insert("menu_plate", row, values);
           return DBHelper.EjecutarIUD(sInsert);
        }
        
        
        
        public static void update(MenuPlate menuPlate)
        {
            string[] row = new string[] { "idMenuPlate", "idPlateMenu", "idTypeMenu" };
            object[] values = new object[] { menuPlate.idPlateMenu, menuPlate.idPlateMenu, menuPlate.idTypeMenu };
            string sUpdate = QueryHelper.update("menu_plate", row, values, "idMenuPlate", menuPlate.idMenuPlate);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("menu_plate", "idMenuPlate", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static MenuPlate getById(int id)
        {
            string select = "select * from menu_plate where idMenuPlate=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            MenuPlate menuPlate;
            if (dt.Rows.Count > 0)
            {
                menuPlate = getByRow(dt.Rows[0]);
                return menuPlate;
            }
            return null;
        }

        public static List<MenuPlate> getAll()
        {
            string select = "select * from menu_plate";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<MenuPlate> list = new List<MenuPlate>();
            MenuPlate menuPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    menuPlate = getByRow(row);
                    list.Add(menuPlate);
                }
                menuPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<PlateMenu> getPlateMenuBranch(int type)
        {
            string select = "select menu_plate.idPlateMenu,  plate_menu.idCategoryPlate,  plate_menu.averageCalification,  plate_menu.name,  plate_menu.price,  plate_menu.description  from menu_plate INNER JOIN plate_menu ON menu_plate.idPlateMenu = plate_menu.idPlateMenu where menu_plate.idTypeMenu=" + type.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PlateMenu> list = new List<PlateMenu>();
            PlateMenu plate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    int id = row.Field<int>("idPlateMenu");
                    plate = PlateMenuData.getById(id);
                    list.Add(plate);
                }
            }
            return list;
        }

        private static MenuPlate getByRow(DataRow row)
        {
            MenuPlate menuPlate = new MenuPlate();
            menuPlate.idPlateMenu = row.Field<int>("idMenuPlate");
            menuPlate.idTypeMenu = row.Field<int>("idTypeMenu");
            menuPlate.idPlateMenu = row.Field<int>("idPlateMenu");
            return menuPlate;
        }
    }
}
