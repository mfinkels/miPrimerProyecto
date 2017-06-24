using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class BranchRestaurantData
    {
        public static void insert(BranchRestaurant branch)
        {
            string[] row = new string[] { "idRangePriceBranch", "idRestaurant", "name", "altitude", "latitude", "averageCalification" };
            object[] values = new object[] { branch.idRangePriceBranch, branch.idRestaurant, branch.name, branch.altitude, branch.latitude, branch.averageCalification };
            string sInsert = QueryHelper.insert("branch_restaurant", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(BranchRestaurant branch)
        {
            string[] row = new string[6] { "idRangePriceBranch", "idRestaurant", "name", "altitude", "latitude", "averageCalification" };
            object[] values = new object[] { branch.idRangePriceBranch, branch.idRestaurant, branch.name, branch.altitude, branch.latitude, branch.averageCalification };
            string sUpdate = QueryHelper.update("branch_restaurant", row, values, "idBranchRestaurant", branch.idBranchRestaurant);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("branch_restaurant", "idBranchRestaurant", id);
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static BranchRestaurant getById(int id)
        {
            string select = "select * from branch_restaurant where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            BranchRestaurant branch;
            if (dt.Rows.Count > 0)
            {
                branch = getByRow(dt.Rows[0]);
                branch.photo = PhotoBranchData.getByBranch(branch.idBranchRestaurant);
                branch.cuisine = CuisineBranchData.getByBranch(branch.idBranchRestaurant);
                //branch.menu = MenuBranch.;
                branch.promotion = BranchPromotionData.getAllByBranch(branch.idBranchRestaurant);
                return branch;
            }
            return null;
        }

        public static List<BranchRestaurant> getAll()
        {
            string select = "select * from branch_restaurant";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<BranchRestaurant> list = new List<BranchRestaurant>();
            BranchRestaurant branch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    branch = getByRow(row);
                    branch.photo = PhotoBranchData.getByBranch(branch.idBranchRestaurant);
                    branch.cuisine = CuisineBranchData.getByBranch(branch.idBranchRestaurant);
                    branch.filter = FilterBranchData.getByBranch(branch.idBranchRestaurant);
                    branch.promotion = BranchPromotionData.getAllByBranch(branch.idBranchRestaurant);
                    list.Add(branch);
                }
                branch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static BranchRestaurant getByRow(DataRow row)
        {
            BranchRestaurant branch = new BranchRestaurant();
            branch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            branch.idRangePriceBranch = row.Field<int>("idRangePriceBranch");
            branch.idRestaurant = row.Field<int>("idRestaurant");
            branch.name = row.Field<string>("name");
            branch.altitude = row.Field<string>("altitude");
            branch.latitude = row.Field<string>("latitude");
            branch.averageCalification = row.Field<int>("averageCalification");
            return branch;
        }
    }
}