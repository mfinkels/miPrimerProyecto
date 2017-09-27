using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class PlateMenuData
    {
       public static void insert(PlateMenu p)
        {
            string[] row = new string[] { "idCategoryPlate", "averageCalification", "name", "price", "description" };
            object[] values = new object[] { p.idCategoryPlate, p.averageCalification, p.name, p.price, p.description };
            string sInsert = QueryHelper.insert("plate_menu", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
     
     public static void update(PlateMenu p)
        {
            string[] row = new string[] { "idCategoryPlate", "averageCalification", "name", "price", "description" };
            object[] values = new object[] { p.idCategoryPlate, p.averageCalification, p.name, p.price, p.description };
            string sUpdate = QueryHelper.update("plate_menu", row, values, "idPlateMenu", p.idPlateMenu);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("plate_menu", "idPlateMenu", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static PlateMenu getById(int id)
        {
            string select = "select * from plate_menu where idPlateMenu=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            PlateMenu p;
            if (dt.Rows.Count > 0)
            {
                p = getByRow(dt.Rows[0]);
                p.photo = PhotoPlateData.getPhotoByPlate(p.idPlateMenu);
                p.category = CategoryPlateData.getById(p.idCategoryPlate);
                p.calification = CalificationPlateData.getAllByPlate(p.idPlateMenu);
                return p;
            }
            return null;
        }

        public static List<PlateMenu> getAll()
        {
            string select = "select * from plate_menu";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PlateMenu> list = new List<PlateMenu>();
            PlateMenu p;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    p = getByRow(row);
                    list.Add(p);
                }
                p = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<PlateMenu> getByBranch()
        {
            string select = "select * from plate_menu";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<PlateMenu> list = new List<PlateMenu>();
            PlateMenu p;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    p = getByRow(row);
                    list.Add(p);
                }
                p = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static PlateMenu getByRow(DataRow row)
        {
            PlateMenu p = new PlateMenu();
            p.idPlateMenu = row.Field<int>("idPlateMenu");
            p.idCategoryPlate = row.Field<int>("idCategoryPlate");
            p.averageCalification = row.Field<double>("averageCalification");
            p.name = row.Field<string>("name");
            p.price = row.Field<double>("price");
            p.description = row.Field<string>("description");
            return p;
        }
    }
}
