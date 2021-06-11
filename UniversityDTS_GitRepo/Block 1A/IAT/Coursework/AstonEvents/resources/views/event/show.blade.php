@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __($event->name) }}</div>
                <div class="card-body">
                    <div class="form-group row">
                        <label for="phone" class="col-md-4 col-form-label text-md-right"><b>{{ __('Description') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $event->description }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="name" class="col-md-4 col-form-label text-md-right"><b>{{ __('Location') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $event->location }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="event_category_id" class="col-md-4 col-form-label text-md-right"><b>{{ __('Category') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $eventCategory->name }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="name" class="col-md-4 col-form-label text-md-right"><b>{{ __('Date/Time') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $event->date_time->format("Y-m-d H:i") }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="name" class="col-md-4 col-form-label text-md-right"><b>{{ __('Interest Ranking') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $event->interest_ranking }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="name" class="col-md-4 col-form-label text-md-right"><b>{{ __('Organiser') }}</b></label>

                        <div class="col-md-6">
                            <p>{{ $organiser->name }}</p>
                            <p>{{ $organiser->phone }}</p>
                            <p>{{ $organiserEmail }}</p>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-md-6 offset-3">
                            @foreach($event->images as $image)
                                <image src="{{ url('storage/' . $image->file_path) }}" alt="Event image" width="100" height="100"></image>
                            @endforeach
                        </div>
                    </div>

                    @if (session()->has('addedInterest' . $event->id . Auth::id()))
                        <div class="form-group row mb-0">
                            <div class="col-md-5 offset-3">
                                <b>Thanks for showing your interest!</b>
                            </div>
                        </div>
                    @else
                        <form method="POST" action="{{ route('student.event.addInterest', $event->id) }}">
                            @csrf
                            <div class="form-group row mb-0">
                                <div class="col-md-4 offset-3">
                                    <button type="submit" class="btn btn-primary float-right">
                                        {{ __('Submit Interest in Event') }}
                                    </button>
                                </div>
                            </div>
                        </form>
                    @endif

                    <form method="GET" action="{{ route('student.event.contact', $event->id) }}">
                        @csrf
                        <button type="submit" class="btn btn-outline-danger float-right">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                                <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"></path>
                            </svg>
                            Contact Event Organiser
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
