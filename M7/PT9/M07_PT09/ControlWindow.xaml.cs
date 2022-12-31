using System;
using System.Collections.Generic;
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
using System.Windows.Shapes;

namespace M07_PT09
{ 
    public partial class ControlWindow
    {
        public ControlWindow()
        {
            InitializeComponent();
        }

        private void Cancelar_Click(object sender, RoutedEventArgs e)
        {
           //Cancelar, si tiene algo pedido, lo borra, y pone la mesa libre
            this.Close();
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            //Cerrar = Cancelar
            MainWindow mainw = new MainWindow();
            mainw.Show();
        }
        private void Guardar_Click(object sender, RoutedEventArgs e)
        {
            //Cancelar, si tiene algo pedido, lo borra, y pone la mesa libre
            MainWindow mainw = new MainWindow();
            mainw.Show();
        }
    }
}
