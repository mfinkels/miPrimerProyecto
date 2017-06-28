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
            string[] row = new string[] { "idRangePriceBranch", "idRestaurant", "name","latitude", "longitude", "averageCalification" };
            object[] values = new object[] { branch.idRangePriceBranch, branch.idRestaurant, branch.name, branch.latitude, branch.longitude, branch.averageCalification };
            string sInsert = QueryHelper.insert("branch_restaurant", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(BranchRestaurant branch)
        {
            string[] row = new string[6] { "idRangePriceBranch", "idRestaurant", "name", "latitude", "longitude", "averageCalification" };
            object[] values = new object[] { branch.idRangePriceBranch, branch.idRestaurant, branch.name, branch.latitude, branch.longitude, branch.averageCalification };
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
                branch.filter = FilterBranchData.getByBranch(branch.idBranchRestaurant);
                branch.promotion = BranchPromotionData.getAllByBranch(branch.idBranchRestaurant);
                branch.menu = TypeMenuData.getMenuByBranch(branch.idBranchRestaurant);
                branch.service = ServiceBranchData.getServicesByRestaurants(branch.idBranchRestaurant);
                branch.timetable = TimetableBranchData.getByBranch(branch.idBranchRestaurant);
                branch.restaurant = RestaurantData.getById(branch.idBranchRestaurant);
                branch.RangePrice = RangePriceBranchData.getById(branch.idRangePriceBranch);
                return branch;
            }
            return null;
        }

        public static List<BranchRestaurant> getAll(int limit, int offset)
        {
            string select = "select * from branch_restaurant LIMIT " + limit.ToString() + " OFFSET " + offset.ToString();
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
                    branch.menu = TypeMenuData.getMenuByBranch(branch.idBranchRestaurant);
                    branch.service = ServiceBranchData.getServicesByRestaurants(branch.idBranchRestaurant);
                    branch.timetable = TimetableBranchData.getByBranch(branch.idBranchRestaurant);
                    branch.restaurant = RestaurantData.getById(branch.idBranchRestaurant);
                    branch.RangePrice = RangePriceBranchData.getById(branch.idRangePriceBranch);
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
            branch.latitude = row.Field<string>("latitude");
            branch.longitude = row.Field<string>("longitude");
            branch.averageCalification = row.Field<double>("averageCalification");
            return branch;
        }
    }
}