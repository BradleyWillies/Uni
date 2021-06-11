<?php

namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;

class ContactMail extends Mailable
{
    use Queueable, SerializesModels;

    private $name;
    private $message;
    private $email;
    private $eventName;

    /**
     * Create a new message instance.
     *
     * @return void
     */
    public function __construct($name, $message, $email, $eventName)
    {
        $this->name = $name;
        $this->message = $message;
        $this->email = $email;
        $this->eventName = $eventName;
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        return $this->markdown('mail.contact', ['name' => $this->name, 'message' => $this->message, 'eventName' => $this->eventName, 'email' => $this->email])
            ->from('bradley.willies@gmail.com')
            ->subject('Aston Events Contact');
    }
}
