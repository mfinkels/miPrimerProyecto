using ApiEjemplo.AddressInfo;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class StateData
    {
        public static void insert(State s)
        {
            string sInsert = "Insert into state (idCountry, name) values (" + Convert.ToString(s.idCountry)+ ",'" + s.name + "')";
            DBHelper.EjecutarIUD(sInsert);
        }
        public static void update(State s)
        {
            string sUpdate = "update state set idCountry=" + Convert.ToString(s.idCountry) + ",name='" + s.name + "' where idState=" + Convert.ToString(s.idState);
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static void Delete(int id)
        {
            string sUpdate = "delete from state where idState=" + id.ToString();
            DBHelper.EjecutarIUD(sUpdate);
        }
        public static State getById(int id)
        {
            string select = "select * from state where idState=" + id.ToString();
            DataTable dt = DBHelper.EjecutarSelect(select);
            State s;
            if (dt.Rows.Count > 0)
            {
                s = getByRow(dt.Rows[0]);
                return s;
            }
            return null;
        }

        public static List<State> getAll()
        {
            string select = "select * from state";
            DataTable dt = DBHelper.EjecutarSelect(select);
            List<State> list = new List<State>();
            State s;
            if (dt.Rows.Count > 0)
            {
                foreach (DataRow row in dt.Rows)
                {
                    s = getByRow(row);
                    list.Add(s);
                }
                s = getByRow(dt.Rows[0]);
            }
            return list;
        }

        private static State getByRow(DataRow row)
        {
            State s = new State();
            s.idState = row.Field<int>("idState");
            s.idCountry = row.Field<int>("idCountry");
            s.name = row.Field<string>("name");
            return s;
        }
    }
}