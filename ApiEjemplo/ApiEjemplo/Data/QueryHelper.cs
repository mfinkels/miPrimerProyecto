using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Data
{
    public class QueryHelper
    {
        public static string insert(string table, string[] row, object[] values) {
            string insert = "insert into " + table + " (";

            for (int i = 0; i < row.Count(); i++) {
                if (i == row.Count()-1) {

                    insert += row[i] + ") values (";
                    for (int j = 0; j < values.Count(); j++) {
                        if (j == values.Count()-1){
                            if (values[j].GetType() == typeof(string))
                            {
                                insert += "'" + values[j] + "'" + ")";
                            }
                            else
                            {
                                insert += values[j] + ")";
                            }
                            
                        }else {
                            if (values[j].GetType() == typeof(string))
                            {
                                insert += "'" + values[j] + "'" + ",";
                            }
                            else {
                                insert += values[j] + ",";
                            }
                            
                        }
                    }
                }
                else {
                    insert += row[i] + ",";
                }
                
            }

            return insert;
        }


        public static string update(string table, string[] row, object[] values, string sId, int id) {
            string update = "update " + table + " set ";
            for (int i = 0; i < row.Count(); i++)
            {
                update += row[i] + "=";
                if (values[i].GetType() == typeof(string))
                {
                    if (values.Count()-1 == i)
                    {
                        update += "'" + values[i] + "'" + " where " + sId + "=" + Convert.ToString(id);
                    }
                    else
                    {
                        update += "'" + values[i] + "'" + ",";
                    }

                }
                else {
                    if (values.Count()-1 == i)
                    {
                        update += values[i] + " where " + sId + "=" + Convert.ToString(id);
                    }
                    else
                    {
                        update += values[i] + ",";
                    }
                }
            }
            return update;
        }


        public static string delete(string table, string sId, int id) {
            string delete = "delete from " + table + " where " + sId + "=" + Convert.ToString(id);
            return delete;
        }

        public static string[] allQueries(string table, string[] row, object[] values, string sId, int id) {
            string[] queries = new string[] { insert(table, row, values), update(table, row, values, sId, id), delete(table, sId, id) };
            return queries;
        }

    }
}