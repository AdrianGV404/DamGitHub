using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace M07_PT09
{

    public partial class MainWindow
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        public static SolidColorBrush Rojo = new SolidColorBrush(Color.FromRgb(255, 130, 130));
        public static SolidColorBrush Verde = new SolidColorBrush(Color.FromRgb(170, 255, 130));
        public static bool en_uso = false;
        private void Mesa1_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa1, 1);
        }
        private void Mesa2_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa2, 2);
        } 
        private void Mesa3_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa3, 3);
        }
        private void Mesa4_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa4, 4);
        }
        private void Mesa5_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa5, 5);
        }
        private void Mesa6_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa6, 6);
        }
        private void Mesa7_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa7, 7);
        }
        private void Mesa8_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa8, 8);
        }
        private void Mesa9_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa9, 9);
        }
        private void Mesa10_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa10, 10);
        }
        private void Mesa11_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa11, 11);
        }
        private void Mesa12_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa12, 12);
        }
        private void Mesa13_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa13, 13);
        }
        private void Mesa14_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa14, 14);
        }
        private void Mesa15_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa15, 15);
        }
        private void Mesa16_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa16, 16);
        }
        private void Mesa17_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa17, 17);
        }
        private void Mesa18_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa18, 18);
        }
        private void Mesa19_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa19, 19);
        }
        private void Mesa20_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa20, 20);
        }
        private void Mesa21_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa21, 21);
        }
        private void Mesa22_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa22, 22);
        }
        private void Mesa23_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa23, 23);
        }
        private void Mesa24_Click(object sender, RoutedEventArgs e)
        {
            cambiar_estado(mesa24, 24);
        }

        public void cambiar_estado(Button mesa,int num) {
            //Comprobar estado con query
            String conn = "Database = 'localhost'";
            String check = "select ocupada from mesa where numero = '1'";
            if (check.Equals("")) { 
            
            }
            //Ocupado (abre control con datos de la orden)
                ControlWindow control = new ControlWindow();
                control.view_mesa.Content = num;
                control.Show();
                this.Hide();

                //No Ocupado (abre control vacio)
                //mesa.Background = Verde;
            }
        }
    }
