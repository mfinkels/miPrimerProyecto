using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CategoryPlateData
    {
        public static void insert(CategoryPlate catPlate)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { catPlate.name};
            string sInsert = QueryHelper.insert("category_plate", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(CategoryPlate catPlate)
        {
            string[] row = new string[] { "name" };
            object[] values = new object[] { catPlate.name };
            string sUpdate = QueryHelper.update("category_plate", row, values, "idCategoryPlate", catPlate.idCategoryPlate);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("category_plate", "idCategoryPlate", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static CategoryPlate getById(int id)
        {
            string select = "select * from category_plate where idCategoryPlate=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            CategoryPlate catPlate;
            if (dt.Rows.Count > 0)
            {
                catPlate = getByRow(dt.Rows[0]);
                return catPlate;
            }
            return null;
        }

        public static CategoryPlate getByIdPlate(int id)
        {
            string select = "select * from category_plate where idPlateMenu=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            CategoryPlate catPlate;
            if (dt.Rows.Count > 0)
            {
                catPlate = getByRow(dt.Rows[0]);
                return catPlate;
            }
            return null;
        }

        public static List<CategoryPlate> getAll()
        {
            string select = "select * from category_plate";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CategoryPlate> list = new List<CategoryPlate>();
            CategoryPlate catPlate;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    catPlate = getByRow(row);
                    list.Add(catPlate);
                }
                catPlate = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static CategoryPlate getByRow(DataRow row)
        {
            CategoryPlate catPlate = new CategoryPlate();
            catPlate.idCategoryPlate = row.Field<int>("idCategoryPlate");
            catPlate.name = row.Field<string>("name");
            return catPlate;
        }
    }
}