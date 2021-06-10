@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Dashboard') }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    <h3>{{ ('Welcome ' . auth()->user()->organiser->name) }}</h3><br>

                        <form method="GET" action="{{ route('dashboard.event.index') }}">
                            <div class="form-group row">
                                <label for="event_category_id" class="col-md-4 col-form-label text-md-right">{{ __('Filter by category') }}</label>

                                <div class="col-md-6">
                                    <select name="event_category_id" id="event_category_id">
                                        <option value="" ></option>
                                        @foreach($eventCategories as $eventCategory)
                                            <option value="{{ $eventCategory->id }}" @if(app('request')->input('event_category_id') == $eventCategory->id) selected @endif>{{ $eventCategory->name }}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="event_heading" class="col-md-4 col-form-label text-md-right">{{ __('Sort by heading') }}</label>

                                <div class="col-md-3">
                                    <select name="event_heading" id="event_heading">
                                        @php($event_heading = app('request')->input('event_heading'))
                                        <option value="" @if(!$event_heading || $event_heading == "") selected @endif></option>
                                        <option value="name" @if($event_heading == "name") selected @endif>Name</option>
                                        <option value="description" @if($event_heading == "description") selected @endif>Description</option>
                                        <option value="location" @if($event_heading == "location") selected @endif>Location</option>
                                        <option value="date_time" @if($event_heading == "date_time") selected @endif>Date/Time</option>
                                        <option value="interest_ranking" @if($event_heading == "interest_ranking") selected @endif>Interest Ranking</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    @php($sort_order = app('request')->input('sort'))
                                    <input type="radio" id="ascending" name="sort" value="asc" @if(!$sort_order || $sort_order == "" || $sort_order == 'asc') checked @endif>
                                    <label for="ascending">Ascending</label><br>
                                    <input type="radio" id="descending" name="sort" value="desc" @if($sort_order == 'desc') checked @endif>
                                    <label for="descending">Descending</label><br>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2.5 offset-4">
                                    <button type="submit" class="btn btn-primary">
                                        {{ __('Search') }}
                                    </button>
                                </div>
                                <div class="col-md-2">
                                    <a href="{{route('dashboard.event.index')}}" class="btn btn-secondary">{{ __('Reset') }}</a>
                                </div>
                                <div class="col-md-3 offset-1">
                                    <a href="{{route('dashboard.event.create')}}" class="btn btn-success float-right">{{ __('Create Event') }}</a>
                                </div>
                            </div>
                        </form>


                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    @if (count($events) > 0)
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th>Name</th><th>Description</th><th>Location</th><th>Date/Time</th><th>Interest Ranking</th>
                            </tr>

                            @foreach ($events as $event)
                                <tr>
                                    <td><a href="{{route("dashboard.event.show", $event->id)}}">{{Str::limit($event['name'], 50)}}</a></td>
                                    <td>{{Str::limit($event['description'], 50)}}</td>
                                    <td>{{Str::limit($event['location'], 50)}}</td>
                                    <td>{{$event['date_time']->format("d-m-Y H:i")}}</td>
                                    <td>{{$event['interest_ranking']}}</td>
                                </tr>
                            @endforeach
                        </table>
                    @endif
                </div>
            </div>

        </div>
    </div>
</div>
@endsection
