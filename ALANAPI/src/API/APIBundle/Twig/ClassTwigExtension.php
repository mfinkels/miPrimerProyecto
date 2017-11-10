<?php
namespace API\APIBundle\Twig;
class ClassTwigExtension extends \Twig_Extension {

   public function getFilters()
    {
        return array(
            new \Twig_SimpleFilter('class', array($this, 'getClass')),
        );
    }

    public function getName() {
        return 'class_twig_extension';
    }

    public function getClass($object) {
        return (new \ReflectionClass($object))->getShortName();
    }

}
