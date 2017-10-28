using ApiEjemplo.AddressInfo;
using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class CuisineBranchData
    {
        public static void insert(CuisineBranch bCuisine)
        {
            string[] row = new string[] { "idCousine", "idBranchRestaurant" };
            object[] values = new object[] { bCuisine.idCousine, bCuisine.idBranchRestaurant };
            string sInsert = QueryHelper.insert("cuisine_branch", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(CuisineBranch bCuisine)
        {
            string[] row = new string[] { "idCousine", "idBranchRestaurant" };
            object[] values = new object[] { bCuisine.idCousine, bCuisine.idBranchRestaurant };
            string sInsert = QueryHelper.update("cuisine_branch", row, values, "idCusineBranch", bCuisine.idCuisineBranch);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("cuisine_branch", "idCusineBranch", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static CuisineBranch getById(int id)
        {
            string select = "select * from cuisine_branch where idCuisineBranch=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            CuisineBranch bCuisine;
            if (dt.Rows.Count > 0)
            {
                bCuisine = getByRow(dt.Rows[0]);
                return bCuisine;
            }
            return null;
        }

        public static List<Cuisine> getByBranch(int id)
        {
            string select = "select * from cuisine_branch where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<Cuisine> list = new List<Cuisine>();
            CuisineBranch bCuisine;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    bCuisine = getByRow(row);
                    bCuisine.cuisine = CuisineData.getById(bCuisine.idCousine);
                    list.Add(bCuisine.cuisine);
                }
                bCuisine = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<CuisineBranch> getAll()
        {
            string select = "select * from cuisine_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<CuisineBranch> list = new List<CuisineBranch>();
            CuisineBranch bCuisine;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    bCuisine = getByRow(row);
                    list.Add(bCuisine);
                }
                bCuisine = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static CuisineBranch getByRow(DataRow row)
        {
            CuisineBranch bCuisine = new CuisineBranch();
            bCuisine.idCuisineBranch = row.Field<int>("idCuisineBranch");
            bCuisine.idCousine = row.Field<int>("idCousine");
            bCuisine.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            return bCuisine;
        }
    }
}