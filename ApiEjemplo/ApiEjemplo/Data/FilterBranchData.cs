using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class FilterBranchData
    {
        public static void insert(FilterBranch fBranch)
        {
            string[] row = new string[] { "idBranchRestaurant", "idTypeFilter" };
            object[] values = new object[] { fBranch.idBranchRestaurant, fBranch.idTypeFilter };
            string sInsert = QueryHelper.insert("filter_branch", row, values);
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(FilterBranch fBranch)
        {
            string[] row = new string[] { "idBranchRestaurant", "idTypeFilter" };
            object[] values = new object[] { fBranch.idBranchRestaurant, fBranch.idTypeFilter };
            string sUpdate = QueryHelper.update("filter_branch", row, values, "idFilterBranch", fBranch.idFilterBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void delete(int id)
        {
            string sUpdate = QueryHelper.delete("filter_branch", "idFilterBranch", id);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static FilterBranch getById(int id)
        {
            string select = "select * from filter_branch where idTypeFilter=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            FilterBranch fBranch;
            if (dt.Rows.Count > 0)
            {
                fBranch = getByRow(dt.Rows[0]);
                return fBranch;
            }
            return null;
        }

        public static List<FilterBranch> getAll()
        {
            string select = "select * from filter_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<FilterBranch> list = new List<FilterBranch>();
            FilterBranch fBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    fBranch = getByRow(row);
                    list.Add(fBranch);
                }
                fBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        public static List<TypeFilter> getByBranch(int id)
        {
            string select = "select * from filter_branch where idBranchRestaurant=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeFilter> list = new List<TypeFilter>();
            FilterBranch fBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    fBranch = getByRow(row);
                    fBranch.filter = TypeFilterData.getById(fBranch.idTypeFilter);
                    list.Add(fBranch.filter);
                }
                fBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static FilterBranch getByRow(DataRow row)
        {
            FilterBranch fBranch = new FilterBranch();
            fBranch.idFilterBranch = row.Field<int>("idFilterBranch");
            fBranch.idBranchRestaurant = row.Field<int>("idBranchRestaurant");
            fBranch.idTypeFilter = row.Field<int>("idTypeFilter");
            return fBranch;
        }
    }
}