package edu.pucmm.sbv.vistas.basico

import com.vaadin.server.ClassResource
import com.vaadin.server.ExternalResource
import com.vaadin.server.FileResource
import com.vaadin.server.Page
import com.vaadin.server.StreamResource
import com.vaadin.server.VaadinService
import com.vaadin.server.VaadinSession
import com.vaadin.ui.Image
import com.vaadin.ui.Label
import com.vaadin.ui.Link
import com.vaadin.ui.VerticalLayout
import edu.pucmm.sbv.vistas.IndexUI

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage

/**
 * Created by vacax on 17/10/16.
 */
class BasicoPanel extends VerticalLayout {

    IndexUI indexUI;

    public BasicoPanel(){

        //Setenado los valores por defectos.
        //Cambiando el titulo
        Page.getCurrent().setTitle("Seteando el Titulo por programación");

        // Seteando el parametro de sesión
        VaadinSession.getCurrent().setAttribute("parametro", "valor");

        // Tomando la información de la ruta d
        File baseDir = VaadinService.getCurrent().getBaseDirectory();
        addComponent(new Label("El directorio: ${baseDir.absolutePath}"))

        //Recuperando imagenes desde el classpath.
        ClassResource classResource = new ClassResource("/static/imagenes/logo_pucmm.png")

        //Recuperando imagen desde una ruta de la PC
        FileResource resource = new FileResource(new File("${baseDir.absolutePath}/imagenes/konoha.jpg"));

        // Creando un objeto de imagen
        Image image = new Image("Imagen desde el classpath", classResource);
        image.setWidth("200px")
        image.setHeight("200px")

        Image imageLocal = new Image("Imagen desde el disco", resource);
        imageLocal.setWidth("200px")
        imageLocal.setHeight("200px")

        StreamResource.StreamSource imagesource = new MiImagenSource ();
        StreamResource resourceStream = new StreamResource(imagesource, "myimage.png");


        // Creando un enlace para la descarga
        Link link = new Link("Enlace a la imagen", classResource);

        addComponent(image)
        addComponent(imageLocal)
        addComponent(new Image("Dede la URL", new ExternalResource("http://static8.comicvine.com/uploads/square_small/11112/111122896/5402107-super_saiyan_blue_goku__5_by_rayzorblade189-dacu6j6.png")))
        addComponent(new Image("Image title", resourceStream))
        addComponent(link);
    }


}

class MiImagenSource implements StreamResource.StreamSource {
    ByteArrayOutputStream imagebuffer = null;
    int reloads = 0;


    public InputStream getStream () {
        /* Create an image and draw something on it. */
        BufferedImage image = new BufferedImage (200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics drawable = image.getGraphics();
        drawable.setColor(Color.lightGray);
        drawable.fillRect(0,0,200,200);
        drawable.setColor(Color.yellow);
        drawable.fillOval(25,25,150,150);
        drawable.setColor(Color.blue);
        drawable.drawRect(0,0,199,199);
        drawable.setColor(Color.black);
        drawable.drawString("Reloads="+reloads, 75, 100);
        reloads++;

        try {
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);

            return new ByteArrayInputStream(
                    imagebuffer.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}