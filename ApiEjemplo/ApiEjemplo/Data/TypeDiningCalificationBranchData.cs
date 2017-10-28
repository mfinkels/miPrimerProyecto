using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class TypeDiningCalificationBranchData
    {
        public static void insert(TypeDiningCalificationBranch tdCalBranch)
        {
            string sInsert = "Insert into type_dining_calification_branch (name, icon) values ('" + tdCalBranch.name + "','" + tdCalBranch.icon + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(TypeDiningCalificationBranch tdCalBranch)
        {
            string sUpdate = "update type_dining_calification_branch set name='" + tdCalBranch.name + "',icon='" + tdCalBranch.icon + "' where idTypeDining=" + Convert.ToString(tdCalBranch.idTypeDiningCalificationBranch);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from type_dining_calification_branch where idTypeDining=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }

        public static TypeDiningCalificationBranch getById(int id)
        {
            string select = "select * from type_dining_calification_branch where idTypeDining=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            TypeDiningCalificationBranch tdCalBranch;
            if (dt.Rows.Count > 0)
            {
                tdCalBranch = getByRow(dt.Rows[0]);
                return tdCalBranch;
            }
            return null;
        }

        public static List<TypeDiningCalificationBranch> getAll()
        {
            string select = "select * from type_dining_calification_branch";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<TypeDiningCalificationBranch> list = new List<TypeDiningCalificationBranch>();
            TypeDiningCalificationBranch tdCalBranch;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    tdCalBranch = getByRow(row);
                    list.Add(tdCalBranch);
                }
                tdCalBranch = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static TypeDiningCalificationBranch getByRow(DataRow row)
        {
            TypeDiningCalificationBranch tdCalBranch = new TypeDiningCalificationBranch();
            tdCalBranch.idTypeDiningCalificationBranch = row.Field<int>("idTypeDiningCalificationBranch");
            tdCalBranch.name = row.Field<string>("name");
            tdCalBranch.icon = row.Field<string>("icon");
            return tdCalBranch;
        }
    }
}