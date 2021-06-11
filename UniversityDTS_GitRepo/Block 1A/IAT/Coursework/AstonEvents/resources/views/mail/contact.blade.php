@component('mail::message')
    ### {{ $name }} sent you a message regarding event: {{ $eventName }}

    {{ $message }}

    Sender's email: {{ $email }}

    - {{ config('app.name') }} -
@endcomponent
