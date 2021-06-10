@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header"><a href="{{route('dashboard.event.index')}}">Dashboard</a> -> {{ __('Create Event') }}</div>

                <div class="card-body">
                    <form method="POST" action="{{ route('dashboard.event.store') }}" enctype="multipart/form-data">
                        @csrf

                        <div class="form-group row">
                            <label for="name" class="col-md-4 col-form-label text-md-right">{{ __('Name') }}</label>

                            <div class="col-md-6">
                                <input id="name" type="text" class="form-control @error('name') is-invalid @enderror" name="name" value="{{ old('name') }}" required autocomplete="name" autofocus>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="phone" class="col-md-4 col-form-label text-md-right">{{ __('Description') }}</label>

                            <div class="col-md-6">
                                <textarea id="description" class="form-control @error('description') is-invalid @enderror" name="description" required autocomplete="description" autofocus>{{ old('description') }}</textarea>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="name" class="col-md-4 col-form-label text-md-right">{{ __('Location') }}</label>

                            <div class="col-md-6">
                                <input id="location" type="text" class="form-control @error('location') is-invalid @enderror" name="location" value="{{ old('location') }}" required autocomplete="location" autofocus>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="event_category_id" class="col-md-4 col-form-label text-md-right">{{ __('Category') }}</label>

                            <div class="col-md-6">
                                <select name="event_category_id" id="event_category_id" required>
                                    @foreach($eventCategories as $eventCategory)
                                        <option value="{{ $eventCategory->id }}" @if(old("event_category_id") == $eventCategory->id) selected @endif>{{ $eventCategory->name }}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="name" class="col-md-4 col-form-label text-md-right">{{ __('Date/Time') }}</label>

                            <div class="col-md-6">
                                <input id="date_time" type="datetime-local" class="form-control @error('date_time') is-invalid @enderror" name="date_time" value="{{ old('date_time') }}" required autocomplete="date_time" autofocus>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="name" class="col-md-4 col-form-label text-md-right">{{ __('EventImage(s)') }}</label>

                            <div class="col-md-6">
                                <input id="images" type="file" class="form-control @error('images') is-invalid @enderror" name="images[]" required autofocus multiple>
                                <small style="font-size: 10px">Select multiple images from the explorer / finder by holding ctrl / ⌘</small>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-2 offset-md-4">
                                <a href="{{route('dashboard.event.index')}}" class="btn btn-secondary">{{ __('Cancel') }}</a>
                            </div>
                            <div class="col-md-3 offset-md-1">
                                <button type="submit" class="btn btn-primary float-right">
                                    {{ __('Create Event') }}
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