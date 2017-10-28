using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TypeFilterData
    {
        public static void insert(TypeFilter tfBranchs)
        {
            string sInsert = "Insert into type_filter (name, icon) values ('" + tfBranchs.name + "','" + tfBranchs.icon + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TypeFilter tfBranch)
        {
            string sUpdate = "update type_filter set name='" + tfBranch.name + "',icon='" + tfBranch.icon + "' where idTypeFilter=" + Convert.ToString(tfBranch.idTypeFilter);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from type_filter where idTypeFilter=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static TypeFilter getById(int id)
        {
            string select = "select * from type_filter where idTypeFilter=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TypeFilter tfBranch;
            if (dt.Rows.Count > 0)
            {
                tfBranch = getByRow(dt.Rows[0]);
                return tfBranch;
            }
            return null;
        }

        public static List<TypeFilter> getAll()
        {
            string select = "select * from type_filter";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeFilter> list = new List<TypeFilter>();
            TypeFilter tfBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tfBranch = getByRow(row);
                    list.Add(tfBranch);
                }
                tfBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TypeFilter getByRow(DataRow row)
        {
            TypeFilter tfBranch = new TypeFilter();
            tfBranch.idTypeFilter = row.Field<int>("idTypeFilter");
            tfBranch.name = row.Field<string>("name");
            tfBranch.icon = row.Field<string>("icon");
            return tfBranch;
        }
    }
}