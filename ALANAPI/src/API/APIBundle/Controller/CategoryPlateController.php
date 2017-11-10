<?php

namespace API\APIBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use API\APIBundle\Entity\CategoryPlate;
use API\APIBundle\Form\CategoryPlateType;

/**
 * CategoryPlate controller.
 *
 */
class CategoryPlateController extends Controller
{

    /**
     * Lists all CategoryPlate entities.
     *
     */
    public function getAction($id) {
        $em = $this->getDoctrine()->getManager();
        if ($id == 0) {
            $entities = $em->getRepository('APIAPIBundle:CategoryPlate')->findAll();
            
            $respuesta = array();
            foreach ($entities as $entity) {
                array_push($respuesta, array('idCategoryPlate' => $entity->getIdCategoryPlate(), 'name' => $entity->getName()));
            }
            $response = new Response(json_encode($respuesta));
        } else {

            $entity = $em->getRepository('APIAPIBundle:CategoryPlate')->find($id);
            $response = new Response(json_encode(array('idCategoryPlate' => $id, 'name' => $entity->getName())), 200);
        }
        $response->headers->set('Content-Type', 'application/json');

        return $response; 
    }

    /**
     * Edits an existing CategoryPlate entity.
     *
     */
    public function putAction(Request $request, $id) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('APIAPIBundle:CategoryPlate')->find($id);

        if (!$entity) {
            $response = new Response(json_encode(array('idCategoryPlate' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $entity->setName($parameters['name']);
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idCategoryPlate' => $id, 'name' => $entity->getName())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Deletes a CategoryPlate entity.
     *
     */
    public function deleteAction( $id) {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('APIAPIBundle:CategoryPlate')->find($id);
        
        if (!$entity) {
            $response = new Response(json_encode(array('idCategoryPlate' => $id, 'error' => 'No se encontro el id')), 500);
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }

        $em->remove($entity);
        $em->flush();
        
        $response = new Response(json_encode(array('idCategoryPlate' => $id)), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * Creates a new CategoryPlate entity.
     *
     */
    public function postAction(Request $request) {
        $parameters = [];
        if ($content = $request->getContent()) {
            $parameters = json_decode($content, true);
        }
        
        $entity = new CategoryPlate();
        $em = $this->getDoctrine()->getManager();
        $entity->setName($parameters['name']);        
        $em->persist($entity);
        $em->flush();

        $response = new Response(json_encode(array('idCategoryPlate' => $entity->getIdCategoryPlate(), 'name' => $entity->getName())), 200);
        $response->headers->set('Content-Type', 'application/json');
        return $response;

    }
}
