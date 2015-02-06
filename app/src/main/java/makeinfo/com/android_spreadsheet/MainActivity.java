package makeinfo.com.android_spreadsheet;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tablefixheaders.TableFixHeaders;
import tablefixheaders.adapters.BaseTableAdapter;


public class MainActivity extends ActionBarActivity {

    private class Types {
        private final String name;
        private final List<Value> list;

        Types(String name) {
            this.name = name;
            list = new ArrayList<Value>();
        }

        public int size() {
            return list.size();
        }

        public Value get(int i) {
            return list.get(i);
        }
    }

    private class Value {
        private final String[] data;

        private Value(String name, String january, String feb, String mar, String april, String may, String june,String july,String Aug,String Sep,String oct,String nov,String dec,String total,String avg) {
            data = new String[] {name,january,feb,mar,april,may,june,july,Aug,Sep,oct,nov,dec,total,avg };
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

       // ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        TableFixHeaders tableFixHeaders = (TableFixHeaders)findViewById(R.id.table);
        BaseTableAdapter baseTableAdapter = new FamilyNexusAdapter(this);
        tableFixHeaders.setAdapter(baseTableAdapter);
    }
    public class FamilyNexusAdapter extends BaseTableAdapter {

        private final Types familys[];
        //These are  the header value for spreadsheet.
        private final String headers[] = {
                "Name",
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
                "Total",
                "Avg"

        };
        //You can set the width of each tile by changing this value
        //110 for 1st column and 80 for other
        private final int[] widths = {  110, 80,80,80,80,80,80,80,80,80,80,80,80,80,80};

        private final float density;

        public FamilyNexusAdapter(Context context) {
            //
            familys = new Types[] {
                    //These are the  seperator or types which is seperated by view
                    //Add new Type if needed
                    new Types("Expanse"),
                    new Types("Balance"),
                    new Types("Others"),
            };

            density = context.getResources().getDisplayMetrics().density;

            //value adding processing to the col,the value is in String.

            familys[0].list.add(new Value("Transportation", "", "", "", "", "", "","","","","","","","",""));
            familys[0].list.add(new Value("Home", "", "", "", "", "", "","","","","","","","",""));
            familys[0].list.add(new Value("Utilities", "", "", "", "", "", "","","","","","","","",""));
            familys[0].list.add(new Value("Health", "", "", "", "", "", "","","","","","","","",""));
            familys[0].list.add(new Value("Entertainment", "", "", "", "", "", "","","","","","","","",""));
            familys[0].list.add(new Value("Miscellaneous", "", "", "", "", "", "","","","","","","","",""));
            familys[1].list.add(new Value("Total Expanse", "", "", "", "", "", "","","","","","","","",""));
            familys[1].list.add(new Value("Total Income", "", "", "", "", "", "","","","","","","","",""));
            familys[1].list.add(new Value("Savings", "", "", "", "", "", "","","","","","","","",""));
          //  familys[1].list.add(new Nexus("", "Samsung", "Jelly Bean", "17", "32 GB", "10\"", "2 GB","","","","","","","",""));
            //familys[2].list.add(new Nexus("", "--", "Honeycomb", "13", "--", "--", "--","","","","","","","",""));
        }


        public int getRowCount()
        {
            //Row Length,Also add value in column
            return 11;
        }


        public int getColumnCount() {
            //Column Length,Also add value in column
            return 14;
        }


        public View getView(int row, int column, View convertView, ViewGroup parent) {
            final View view;
            switch (getItemViewType(row, column)) {
                case 0:
                    view = getFirstHeader(row, column, convertView, parent);
                    break;
                case 1:
                    view = getHeader(row, column, convertView, parent);
                    break;
                case 2:
                    view = getFirstBody(row, column, convertView, parent);
                    break;
                case 3:
                    view = getBody(row, column, convertView, parent);
                    break;
                case 4:
                    view = getFamilyView(row, column, convertView, parent);
                    break;
                default:
                    throw new RuntimeException("wtf?");
            }
            return view;
        }

        private View getFirstHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header_first, parent, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[0]);
            return convertView;
        }

        private View getHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header, parent, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[column + 1]);
            return convertView;
        }

        private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_first, parent, false);
            }
            convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
            return convertView;
        }

        private View getBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table, parent, false);
            }
            /*if(row == 8){
                convertView.setBackgroundResource(R.color.row8); //if row8 need different colour
            }*/else {
                //Change table color using bg_table_col
                convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
            return convertView;
        }

        private View getFamilyView(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_family, parent, false);
            }
            final String string;
            if (column == -1) {
                string = getFamily(row).name;
            } else {
                string = "";
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(string);
            return convertView;
        }


        public int getWidth(int column) {
            return Math.round(widths[column + 1] * density);
        }


        public int getHeight(int row) {
            final int height;
            if (row == -1) {
                height = 35;
            } else if (isFamily(row)) {
                height = 25;
            } else {
                height = 45;
            }
            return Math.round(height * density);
        }


        public int getItemViewType(int row, int column) {
            final int itemViewType;
            if (row == -1 && column == -1) {
                itemViewType = 0;
            } else if (row == -1) {
                itemViewType = 1;
            } else if (isFamily(row)) {
                itemViewType = 4;
            } else if (column == -1) {
                itemViewType = 2;
            } else {
                itemViewType = 3;
            }
            return itemViewType;
        }

        private boolean isFamily(int row) {
            int family = 0;
            while (row > 0) {
                row -= familys[family].size() + 1;
                family++;
            }
            return row == 0;
        }

        private Types getFamily(int row) {
            int family = 0;
            while (row >= 0) {
                row -= familys[family].size() + 1;
                family++;
            }
            return familys[family - 1];
        }

        private Value getDevice(int row) {
            int family = 0;
            while (row >= 0) {
                row -= familys[family].size() + 1;
                family++;
            }
            family--;
            return familys[family].get(row + familys[family].size());
        }


        public int getViewTypeCount() {
            return 5;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
