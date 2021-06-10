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

                    <form method="POST" action="{{ route('student.event.addInterest', $event->id) }}">
                        @csrf
                        <div class="form-group row mb-0">
                            <div class="col-md-3 offset-3">
                                <button type="submit" class="btn btn-primary float-right">
                                    {{ __('Submit Interest') }}
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
